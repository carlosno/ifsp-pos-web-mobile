package com.example.elementosinferface

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elementosinferface.ui.theme.ElementosInferfaceTheme
import kotlinx.coroutines.delay
import java.util.function.IntConsumer
import androidx.compose.material3.Icon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElementosInferfaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Formulario(
                        modifier = Modifier
                            .padding(paddingValues = innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(modifier: Modifier) {
    var nome by rememberSaveable() { mutableStateOf(value = "") }
    var tema by rememberSaveable() { mutableStateOf(value = "") }
    var news by rememberSaveable() { mutableStateOf(value = false) }
    var nivel by rememberSaveable() { mutableStateOf(value = 10F) }
    var notificacoes by rememberSaveable() { mutableStateOf(value = true) }

    Column(
        modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        Text(text = "Tema")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = tema == "Claro",
                onClick = { tema = "Claro" },
            )
            Text(text = "Claro")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = tema == "Escuro",
                onClick = { tema = "Escuro" },
            )
            Text(text = "Escuro")
        }
        Text(text = "Receber notificações")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = notificacoes, onCheckedChange = { notificacoes = it })
        }

        Text(text = "Experiencia:${nivel.toInt()}")
        Slider(
            value = nivel, onValueChange = { nivel = it },
            valueRange = 0f..10f
        )

        Button(onClick = {

            println(nome)
            println(tema)
            println(news)
            println(nivel.toInt())
            println(notificacoes)
        }) {
            Text(text = "Salvar Preferencias")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ElementosInferfaceTheme {
        Greeting("Android")
    }
}