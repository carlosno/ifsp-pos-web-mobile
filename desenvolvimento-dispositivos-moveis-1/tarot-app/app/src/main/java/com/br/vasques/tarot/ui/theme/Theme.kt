package com.br.vasques.tarot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TarotColors = darkColorScheme(
    primary = Color(0xFFD0A9F5),
    onPrimary = Color(0xFF2B1846),
    primaryContainer = Color(0xFF4A3670),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFFFD166),
    background = Color(0xFF14101F),
    onBackground = Color(0xFFEDE7F6),
    surface = Color(0xFF14101F),
    onSurface = Color(0xFFEDE7F6),
    surfaceVariant = Color(0xFF2A2140),
    onSurfaceVariant = Color(0xFFE8DEF8)
)

@Composable
fun TarotTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = TarotColors, content = content)
}
