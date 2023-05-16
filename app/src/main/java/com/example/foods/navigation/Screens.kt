package com.example.foods.navigation

sealed class Screen(val route: String) {
    object FoodRecipeListGraph : Screen("Food recipe list logged user")
    object FoodRecipeLoginGraph : Screen("Food recipe login graph")
    object RecipesList : Screen("Recipe list")
    object Details : Screen("Details")
    object MapDetails : Screen("Map details")
    object Profile: Screen("Profile")
    object FoodRecipeRecordVideo: Screen("Food recipe video")
    object FoodRecipeVideoPreview: Screen("Food recipe video")
    object ProfilePicture: Screen("Profile picture")
    object Login: Screen("Login")
}
