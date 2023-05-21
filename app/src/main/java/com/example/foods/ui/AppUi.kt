package com.example.foods.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foods.navigation.Screen

@Composable
fun FoodRecipesTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "RECIPE FOODS",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        })
}

@Composable
fun FoodRecipesBottomNavigation(navController: NavHostController, onClick: (Screen) -> Unit) {
    val items = listOf(
        Screen.RecipesList,
        Screen.Profile,
    )
    androidx.compose.material.BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen.route) {
                        Screen.Profile.route -> {
                            Icon(Icons.Filled.AccountCircle, contentDescription = null)
                        }

                        Screen.RecipesList.route -> {
                            Icon(Icons.Filled.Home, contentDescription = null)
                        }
                    }

                },
                label = { Text(screen.route) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { onClick.invoke(screen) }
            )
        }
    }
}
