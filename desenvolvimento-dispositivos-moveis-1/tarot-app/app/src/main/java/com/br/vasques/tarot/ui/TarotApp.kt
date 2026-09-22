package com.br.vasques.tarot.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.vasques.tarot.ui.screens.CardsListScreen
import com.br.vasques.tarot.ui.screens.DailyCardScreen
import com.br.vasques.tarot.ui.screens.SpreadScreen

enum class Destination(val route: String, val label: String, val emoji: String) {
    Daily("daily", "Carta do Dia", "🌙"),
    Spread("spread", "Tiragem", "🔮"),
    Cards("cards", "Cartas", "📜")
}

@Composable
fun TarotApp(vm: TarotViewModel = viewModel()) {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                Destination.entries.forEach { dest ->
                    NavigationBarItem(
                        selected = current == dest.route,
                        onClick = {
                            nav.navigate(dest.route) {
                                popUpTo(nav.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Text(dest.emoji) },
                        label = { Text(dest.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Destination.Daily.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Destination.Daily.route) { DailyCardScreen(vm) }
            composable(Destination.Spread.route) { SpreadScreen(vm) }
            composable(Destination.Cards.route) { CardsListScreen() }
        }
    }
}
