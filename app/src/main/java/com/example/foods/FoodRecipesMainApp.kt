package com.example.foods

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foods.feature.location.FoodRecipeMapDetailsScreen
import com.example.foods.features.details.FoodRecipeDetailsScreen
import com.example.foods.features.home.FoodRecipesHomeScreen

@Composable
fun FoodRecipesMainApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            FoodRecipesHomeScreen {  id ->
                navController.navigate("${Screen.Details.route}/$id")
            }
        }

        composable(route = "${Screen.Details.route}/{food_recipe_id}") {
            FoodRecipeDetailsScreen {  id ->
                navController.navigate("${Screen.DetailsMap.route}/$id")
            }
        }

        composable(route = "${Screen.DetailsMap.route}/{food_recipe_id}") {
            FoodRecipeMapDetailsScreen()
        }
    }
}
