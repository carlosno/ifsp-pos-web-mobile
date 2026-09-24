package com.br.vasques.tarot.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.br.vasques.tarot.data.TarotCard
import com.br.vasques.tarot.data.cardImageModel

@Composable
fun TarotCardView(
    card: TarotCard,
    reversed: Boolean,
    modifier: Modifier = Modifier,
    label: String? = null,
    showDescription: Boolean = true,
    // Novos parâmetros para a IA
    aiReadingText: String? = null,
    isAiLoading: Boolean = false,
    onAiReadingClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (label != null) {
                Text(
                    text = label.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.height(4.dp))
            }

            Text(
                text = card.numeral,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(6.dp))

            val context = LocalContext.current
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(cardImageModel(context, card))
                    .listener(
                        onError = { _, result ->
                            Log.e("TarotImage", "Falha ao carregar ", result.throwable)
                        }
                    )
                    .build(),
                contentDescription = card.name,
                contentScale = ContentScale.Fit,
                loading = {
                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .aspectRatio(0.6f)
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.width(24.dp))
                    }
                },
                modifier = Modifier
                    .width(130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .rotate(if (reversed) 180f else 0f)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = card.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (reversed) {
                Text(
                    text = "(Invertida)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(Modifier.height(2.dp))

            Text(
                text = card.keywords,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )

            if (showDescription) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (reversed) card.reversed else card.upright,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }

            // --- NOVA SEÇÃO DE INTELIGÊNCIA ARTIFICIAL ---

            // Só exibe o botão se a função de clique for passada
            if (onAiReadingClick != null) {
                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = onAiReadingClick,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isAiLoading // Desativa o botão enquanto carrega
                ) {
                    Text(if (isAiLoading) "Consultando os Astros..." else "✨ Interpretar com IA")
                }

                // Caixa de carregamento ou exibição da resposta
                if (isAiLoading) {
                    Spacer(Modifier.height(12.dp))
                    CircularProgressIndicator(modifier = Modifier.width(32.dp))
                } else if (!aiReadingText.isNullOrEmpty()) {
                    Spacer(Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = aiReadingText,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}