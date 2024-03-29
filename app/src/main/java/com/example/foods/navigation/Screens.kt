package com.example.foods.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details")
    object DetailsMap : Screen("details map")
}
