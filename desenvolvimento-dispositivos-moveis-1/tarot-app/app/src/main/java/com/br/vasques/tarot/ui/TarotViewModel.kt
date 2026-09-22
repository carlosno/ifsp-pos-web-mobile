package com.br.vasques.tarot.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.br.vasques.tarot.data.DAY_MS
import com.br.vasques.tarot.data.DailyDraw
import com.br.vasques.tarot.data.Reading
import com.br.vasques.tarot.data.SpreadCard
import com.br.vasques.tarot.data.TarotDeck
import com.br.vasques.tarot.data.TarotRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class TarotViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = TarotRepository(app)

    val daily: StateFlow<DailyDraw?> =
        repo.daily.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val readings: StateFlow<List<Reading>> =
        repo.readings.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    /** Tiragem atual, ainda não salva. */
    var draft by mutableStateOf<List<SpreadCard>>(emptyList())
        private set

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
        draft = TarotDeck.cards.shuffled().take(3).map { SpreadCard(it.id, Random.nextBoolean()) }
    }

    fun saveReading(note: String) {
        if (draft.isEmpty()) return
        val now = System.currentTimeMillis()
        val reading = Reading(now, now, draft, note.trim())
        draft = emptyList()
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
}
