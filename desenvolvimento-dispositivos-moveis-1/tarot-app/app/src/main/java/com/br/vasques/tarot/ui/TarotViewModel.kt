package com.br.vasques.tarot.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.br.vasques.tarot.data.DAY_MS
import com.br.vasques.tarot.data.DailyDraw
import com.br.vasques.tarot.data.LlmLocalService
import com.br.vasques.tarot.data.Reading
import com.br.vasques.tarot.data.SpreadCard
import com.br.vasques.tarot.data.TarotDeck
import com.br.vasques.tarot.data.TarotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class TarotViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = TarotRepository(app)

    // Serviço da IA instanciado com o contexto da aplicação
    private val localLlmService = LlmLocalService(app)

    val daily: StateFlow<DailyDraw?> =
        repo.daily.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val readings: StateFlow<List<Reading>> =
        repo.readings.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    /** Tiragem atual, ainda não salva. */
    var draft by mutableStateOf<List<SpreadCard>>(emptyList())
        private set

    // ---------- Estados da Inteligência Artificial ----------
    private val _aiReadingText = MutableStateFlow<String?>(null)
    val aiReadingText: StateFlow<String?> = _aiReadingText

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading: StateFlow<Boolean> = _isAiLoading

    init {
        // Inicializa o modelo GGUF em background quando o ViewModel é criado
        viewModelScope.launch {
            localLlmService.initializeModel()
        }
    }

    // ---------- Carta do dia ----------
    fun drawDaily() {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val current = repo.daily.first()
            if (current != null && now - current.drawnAt < DAY_MS) return@launch
            val card = TarotDeck.cards.random()
            repo.saveDaily(DailyDraw(card.id, Random.nextBoolean(), now))
        }
    }

    // ---------- Tiragem de 3 cartas ----------
    fun drawSpread() {
        draft = TarotDeck.cards.shuffled().take(3).map { SpreadCard(cardId = it.id, Random.nextBoolean()) }
        // Limpa a leitura da IA anterior sempre que uma nova tiragem é feita
        _aiReadingText.value = null
    }

    // ---------- Leitura com IA ----------
    fun requestSpreadAiReading(userQuestion: String) {
        if (draft.size < 3) return

        viewModelScope.launch {
            _isAiLoading.value = true
            _aiReadingText.value = null

            val pastCard = TarotDeck.cards.first { it.id == draft[0].cardId }
            val presentCard = TarotDeck.cards.first { it.id == draft[1].cardId }
            val futureCard = TarotDeck.cards.first { it.id == draft[2].cardId }

            // NOVO: Pega o significado exato da carta no nosso baralho (TarotDeck)
            val pastMeaning = if(draft[0].reversed) pastCard.reversed else pastCard.upright
            val presentMeaning = if(draft[1].reversed) presentCard.reversed else presentCard.upright
            val futureMeaning = if(draft[2].reversed) futureCard.reversed else futureCard.upright

            // Envia o Nome + O Significado para a IA ler
            val pastName = "${pastCard.name} - Significado: $pastMeaning"
            val presentName = "${presentCard.name} - Significado: $presentMeaning"
            val futureName = "${futureCard.name} - Significado: $futureMeaning"

            val result = localLlmService.getTarotReading(pastName, presentName, futureName, userQuestion)

            result.onSuccess { text ->
                _aiReadingText.value = text
            }.onFailure { error ->
                _aiReadingText.value = "Erro ao consultar os astros: ${error.message}"
            }

            _isAiLoading.value = false
        }
    }

    // ---------- Gestão de Dados ----------
    fun saveReading(note: String) {
        if (draft.isEmpty()) return
        val now = System.currentTimeMillis()
        // Adiciona a leitura da IA (se existir) à nota guardada
        val finalNote = if (!aiReadingText.value.isNullOrEmpty()) {
            "$note\n\n--- Leitura da IA ---\n${aiReadingText.value}".trim()
        } else {
            note.trim()
        }

        val reading = Reading(now, now, draft, finalNote)
        draft = emptyList()
        _aiReadingText.value = null // Limpa o estado após guardar

        viewModelScope.launch { repo.saveReadings(listOf(reading) + readings.value) }
    }

    fun updateNote(id: Long, note: String) {
        viewModelScope.launch {
            repo.saveReadings(readings.value.map { if (it.id == id) it.copy(note = note.trim()) else it })
        }
    }

    fun deleteReading(id: Long) {
        viewModelScope.launch { repo.saveReadings(readings.value.filterNot { it.id == id }) }
    }

    override fun onCleared() {
        super.onCleared()
        localLlmService.close() // Liberta o modelo da memória ao fechar
    }
}