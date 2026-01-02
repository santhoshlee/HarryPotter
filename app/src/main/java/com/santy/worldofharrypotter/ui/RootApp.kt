package com.santy.worldofharrypotter.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.santy.worldofharrypotter.ui.books.BookDetailScreen
import com.santy.worldofharrypotter.ui.character.CharacterDetailScreen
import com.santy.worldofharrypotter.ui.home.HomeScreen

@Composable
fun RootApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_tabs"
    ) {

        composable("main_tabs") {
            HomeScreen(rootNavController = navController)
        }

        composable(
            route = "book_detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType }),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(150)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(150)) }
        ) {
            BookDetailScreen(onBack = { navController.popBackStack() })
        }

        composable(
            route = "character_detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType }),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(150)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(150)) }
        ) {
            CharacterDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}