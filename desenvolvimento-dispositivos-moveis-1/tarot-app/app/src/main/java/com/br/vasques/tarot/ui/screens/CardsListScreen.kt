package com.br.vasques.tarot.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.br.vasques.tarot.data.TarotCard
import com.br.vasques.tarot.data.TarotDeck
import com.br.vasques.tarot.data.cardImageModel

@Composable
fun CardsListScreen() {
    var query by rememberSaveable { mutableStateOf("") }
    val filtered = remember(query) {
        TarotDeck.cards.filter {
            it.name.contains(query, ignoreCase = true) ||
                it.keywords.contains(query, ignoreCase = true)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Todas as Cartas", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Toque em uma carta para ver o significado.",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar carta") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        items(filtered, key = { it.id }) { card -> CardListItem(card) }
    }
}

@Composable
private fun CardListItem(card: TarotCard) {
    var expanded by rememberSaveable(card.id) { mutableStateOf(false) }
    Card(modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val context = LocalContext.current
                AsyncImage(
                    model = cardImageModel(context, card),
                    contentDescription = card.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(44.dp)
                        .aspectRatio(0.6f)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
                )
                Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                    Text("${card.numeral} · ${card.name}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold)
                    Text(card.keywords, style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic)
                }
            }
            if (expanded) {
                Spacer(Modifier.height(12.dp))
                Text("Significado", style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary)
                Text(card.upright, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Text("Invertida", style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary)
                Text(card.reversed, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
