package com.example.caraoucoroa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caraoucoroa.ui.theme.CaraOuCoroaTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaraOuCoroaTheme {
/*                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> CaraOuCoroaApp(modifier = Modifier.padding(innerPadding))}
            }
        }
    }
}

@Composable
fun CaraOuCoroaApp(modifier: Modifier = Modifier) {
    var resultado by remember(){ mutableStateOf(value ="") }
    Column(modifier = modifier.fillMaxSize(),
    Arrangement.Center,
    Alignment.CenterHorizontally

        ) { Text(text = "🎲 DADO 🎲")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val numero = Random.nextInt(1, 7)
            resultado = when (numero) { 1 -> "⚀" 2 -> "⚁" 3 -> "⚂" 4 -> "⚃" 5 -> "⚄" 6 -> "⚅" else -> "" }
        }) {Text(text = "🎲 Jogar") }
        Spacer(Modifier.height(height = 16.dp))
        Text(text = resultado,fontSize = 100.sp)
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
    CaraOuCoroaTheme {
      /*  Greeting("Android")*/
          CaraOuCoroaApp()
    }
}