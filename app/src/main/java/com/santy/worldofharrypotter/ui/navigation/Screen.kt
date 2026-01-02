package com.santy.worldofharrypotter.ui.navigation

sealed class Screen(val route: String) {
    object Books : Screen("books")
    object Characters : Screen("characters")
    object Houses : Screen("houses")
    object Spells : Screen("spells")

    // Detail route with argument
    object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: Int) = "book_detail/$bookId"
    }

    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun createRoute(characterId: Int) = "character_detail/$characterId"
    }
}