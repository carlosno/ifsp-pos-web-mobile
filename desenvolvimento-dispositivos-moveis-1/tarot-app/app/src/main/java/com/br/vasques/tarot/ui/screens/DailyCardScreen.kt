package com.br.vasques.tarot.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.br.vasques.tarot.data.DAY_MS
import com.br.vasques.tarot.data.TarotDeck
import com.br.vasques.tarot.ui.TarotViewModel
import com.br.vasques.tarot.ui.components.TarotCardView
import kotlinx.coroutines.delay

@Composable
fun DailyCardScreen(vm: TarotViewModel) {
    val daily by vm.daily.collectAsState()
    val draw = daily

    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            now = System.currentTimeMillis()
            delay(1000)
        }
    }

    val remaining = if (draw != null) draw.drawnAt + DAY_MS - now else 0L
    val locked = draw != null && remaining > 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Carta do Dia", style = MaterialTheme.typography.headlineMedium)

        if (locked && draw != null) {
            TarotCardView(TarotDeck.byId(draw.cardId), draw.reversed)
            CountdownCard(remaining)
        } else {
            Text(
                "Respire fundo, foque na sua intenção e sorteie a carta que vai guiar o seu dia.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text("🔮", style = MaterialTheme.typography.displayLarge)
            Button(onClick = { vm.drawDaily() }, modifier = Modifier.fillMaxWidth()) {
                Text("Sortear carta do dia")
            }
        }
    }
}

@Composable
private fun CountdownCard(remainingMs: Long) {
    val total = remainingMs / 1000
    val text = "%02d:%02d:%02d".format(total / 3600, (total % 3600) / 60, total % 60)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Próximo sorteio em", style = MaterialTheme.typography.labelLarge)
            Text(text, style = MaterialTheme.typography.displaySmall)
        }
    }
}
