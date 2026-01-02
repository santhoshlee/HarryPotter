package com.santy.worldofharrypotter.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santy.worldofharrypotter.R
import com.santy.worldofharrypotter.ui.books.BooksScreen
import com.santy.worldofharrypotter.ui.character.CharacterScreen
import com.santy.worldofharrypotter.ui.houses.HousesScreen
import com.santy.worldofharrypotter.ui.navigation.Screen
import com.santy.worldofharrypotter.ui.spell.SpellsScreen
import com.santy.worldofharrypotter.util.NetworkMonitor

@Composable
fun HomeScreen(rootNavController: NavController) {

    val nestedNavController = rememberNavController()
    val networkMonitor = NetworkMonitor(LocalContext.current)
    val isConnected by networkMonitor.isConnected.collectAsState()

    Scaffold(
        topBar = {
            Text(
                text = stringResource(id = R.string.app_title),
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        bottomBar = {
            Column {
                if (!isConnected) NoInternetBanner()
                BottomNav(nestedNavController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = Screen.Books.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Books.route) {
                BooksScreen(onItemClick = { bookId ->
                    rootNavController.navigate(Screen.BookDetail.createRoute(bookId))
                })
            }
            composable(Screen.Characters.route) {
                CharacterScreen(onItemClick = { characterId ->
                    rootNavController.navigate(Screen.CharacterDetail.createRoute(characterId))
                })
            }

            composable(Screen.Houses.route) { HousesScreen() }

            composable(Screen.Spells.route) { SpellsScreen() }

        }
    }
}
