package com.example.foods.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foods.navigation.Screen
import com.mamabe.features.login.FoodRecipeLoginScreen
import com.mamabe.features.signup.SignupScreen

fun NavGraphBuilder.foodRecipeLoginGraph(
    navController: NavHostController
) {
    navigation(route = Screen.FoodRecipeLoginGraph.route, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            FoodRecipeLoginScreen({
                navController.navigate(Screen.FoodRecipeListGraph.route)
            }, {
                navController.navigate(Screen.Signup.route)
            })
        }

        composable(Screen.Signup.route) {
            SignupScreen()
        }
    }
}
