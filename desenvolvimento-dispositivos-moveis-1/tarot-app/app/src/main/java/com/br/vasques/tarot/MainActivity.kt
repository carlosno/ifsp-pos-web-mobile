package com.br.vasques.tarot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.br.vasques.tarot.ui.TarotApp
import com.br.vasques.tarot.ui.theme.TarotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarotTheme { TarotApp() }
        }
    }
}
