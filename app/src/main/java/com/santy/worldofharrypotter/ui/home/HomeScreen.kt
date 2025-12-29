package com.santy.worldofharrypotter.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santy.worldofharrypotter.ui.books.BooksScreen
import com.santy.worldofharrypotter.util.NetworkMonitor

@Composable
fun HomeScreen() {

    val navController = rememberNavController()
    val networkMonitor = NetworkMonitor(LocalContext.current)
    val isConnected by networkMonitor.isConnected.collectAsState()

    Scaffold(
        bottomBar = {
            Column {
                if (!isConnected) {
                    NoInternetBanner()
                }
                BottomNav(navController)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "books",
            modifier = Modifier.padding(padding)
        ) {
            composable("books") { BooksScreen() }
            composable("characters") { EmptyView("Characters") }
            composable("houses") { EmptyView("Houses") }
            composable("spells") { EmptyView("Spells") }
        }
    }
}