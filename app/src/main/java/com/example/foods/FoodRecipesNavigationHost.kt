package com.example.foods

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.foods.navigation.Screen
import com.example.foods.ui.FoodRecipesBottomNavigation
import com.example.foods.ui.FoodRecipesTopBar
import com.example.foods.navigation.graphs.foodRecipeLoginGraph
import com.example.foods.navigation.graphs.foodRecipeUserLoggedGraph

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FoodRecipesNavigationHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val isLoggedUser = false
    val startDestination =
        if (isLoggedUser) Screen.FoodRecipeListGraph.route else Screen.FoodRecipeLoginGraph.route

    Scaffold(
        topBar = { if (isLoggedUser) FoodRecipesTopBar() },
        bottomBar = {
            if (isLoggedUser) FoodRecipesBottomNavigation(navController) { screen ->
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        })
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
            builder = {
                foodRecipeLoginGraph(navController)
                foodRecipeUserLoggedGraph(navController, context)
            })
    }
}
