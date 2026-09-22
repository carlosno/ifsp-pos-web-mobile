package com.br.vasques.tarot.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.br.vasques.tarot.data.Reading
import com.br.vasques.tarot.data.SPREAD_POSITIONS
import com.br.vasques.tarot.data.TarotDeck
import com.br.vasques.tarot.ui.TarotViewModel
import com.br.vasques.tarot.ui.components.TarotCardView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SpreadScreen(vm: TarotViewModel) {
    val readings by vm.readings.collectAsState()
    val draft = vm.draft
    var note by remember { mutableStateOf("") }
    var editing by remember { mutableStateOf<Reading?>(null) }
    var readingToDelete by remember { mutableStateOf<Reading?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Cabeçalho
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Tiragem de 3 Cartas",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Passado, presente e futuro: veja o que cada carta revela sobre a sua pergunta.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Botão de sorteio
        item {
            Button(
                onClick = {
                    note = ""
                    vm.drawSpread()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (draft.isEmpty()) "Tirar 3 cartas" else "Tirar novamente")
            }
        }

        // Cartas sorteadas
        if (draft.isNotEmpty()) {
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(draft) { index, spreadCard ->
                        Column(modifier = Modifier.width(160.dp)) {
                            TarotCardView(
                                card = TarotDeck.byId(spreadCard.cardId),
                                reversed = spreadCard.reversed,
                                label = SPREAD_POSITIONS.getOrElse(index) { "Posição ${index + 1}" }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Anotação da leitura (opcional)") },
                    placeholder = { Text("Ex: Reflexão sobre novos projetos...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Button(
                    onClick = {
                        vm.saveReading(note.trim())
                        note = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar leitura")
                }
            }
        }

        // Seção de histórico
        item {
            Text(
                text = "Minhas leituras",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (readings.isEmpty()) {
            item {
                Text(
                    text = "Nenhuma leitura salva ainda.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            items(readings, key = { it.id }) { reading ->
                ReadingItem(
                    reading = reading,
                    onEdit = { editing = reading },
                    onDelete = { readingToDelete = reading }
                )
            }
        }
    }

    // Diálogo de Edição de Anotação
    editing?.let { reading ->
        EditNoteDialog(
            initial = reading.note,
            onDismiss = { editing = null },
            onSave = { updatedNote ->
                vm.updateNote(reading.id, updatedNote)
                editing = null
            }
        )
    }

    // Diálogo de Confirmação de Exclusão
    readingToDelete?.let { reading ->
        AlertDialog(
            onDismissRequest = { readingToDelete = null },
            title = { Text("Excluir leitura") },
            text = { Text("Tem certeza de que deseja remover esta leitura do histórico?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        vm.deleteReading(reading.id)
                        readingToDelete = null
                    }
                ) {
                    Text("Excluir", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { readingToDelete = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun ReadingItem(
    reading: Reading,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val dateText = remember(reading.createdAt) {
        val format = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale("pt", "BR"))
        format.format(Date(reading.createdAt))
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dateText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            reading.cards.forEachIndexed { index, spreadCard ->
                val card = TarotDeck.byId(spreadCard.cardId)
                val suffix = if (spreadCard.reversed) " (invertida)" else ""
                val posLabel = SPREAD_POSITIONS.getOrElse(index) { "Posição ${index + 1}" }
                Text(
                    text = "$posLabel: ${card.name}$suffix",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (reading.note.isNotBlank()) {
                Text(
                    text = "“${reading.note}”",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onEdit) {
                    Text("Editar")
                }
                TextButton(onClick = onDelete) {
                    Text("Excluir", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
private fun EditNoteDialog(
    initial: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var text by remember(initial) { mutableStateOf(initial) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar anotação") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Anotação") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(onClick = { onSave(text.trim()) }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}