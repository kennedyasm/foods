package com.example.presentation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.details.FoodRecipeDetailsScreen
import com.example.presentation.home.FoodRecipesHomeScreen
import com.example.presentation.map.FoodRecipeMapDetailsScreen

@Composable
fun FoodRecipesMainApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            FoodRecipesHomeScreen { screen, id ->
                navController.navigate("${screen.route}/$id")
            }
        }

        composable(route = "${Screen.Details.route}/{food_recipe_id}") {
            FoodRecipeDetailsScreen { screen, id ->
                navController.navigate("${screen.route}/$id")
            }
        }

        composable(route = "${Screen.DetailsMap.route}/{food_recipe_id}") {
            FoodRecipeMapDetailsScreen()
        }
    }
}
