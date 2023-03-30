package com.example.foods.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foods.feature.location.FoodRecipeLocationMapScreen
import com.example.foods.features.details.FoodRecipeDetailsScreen
import com.example.foods.features.home.FoodRecipesHomeScreen
import com.example.foods.navigation.NavParamNames.FOOD_RECIPE_ID

@Composable
fun FoodRecipesNavHost() {

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

        composable(route = "${Screen.Details.route}/$FOOD_RECIPE_ID") {
            FoodRecipeDetailsScreen {  id ->
                navController.navigate("${Screen.DetailsMap.route}/$id")
            }
        }

        composable(route = "${Screen.DetailsMap.route}/$FOOD_RECIPE_ID") {
            FoodRecipeLocationMapScreen()
        }
    }
}
