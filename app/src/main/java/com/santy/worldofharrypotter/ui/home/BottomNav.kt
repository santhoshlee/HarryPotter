package com.santy.worldofharrypotter.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNav(navController: NavController) {

    val items = listOf(
        "books" to Icons.AutoMirrored.Filled.MenuBook,
        "characters" to Icons.Default.Person,
        "houses" to Icons.Default.Home,
        "spells" to Icons.Default.AutoFixHigh
    )

    NavigationBar {
        items.forEach { (route, icon) ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(route) },
                icon = { Icon(icon, null) },
                label = { Text(route) }
            )
        }
    }

}