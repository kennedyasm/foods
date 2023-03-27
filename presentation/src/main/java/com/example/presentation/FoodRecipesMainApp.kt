package com.example.presentation


import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.State
import com.example.domain.models.FoodRecipeItemUi
import com.example.presentation.details.FoodRecipeDetailsScreen
import com.example.presentation.home.HomeScreenView
import com.example.presentation.viewmodel.FoodRecipesViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
            Card {
                Text(text = "lshsñihs")
                CircularProgressIndicator()

            }
        }
    }
}

@Composable
fun FoodRecipesHomeScreen(
    viewModel: FoodRecipesViewModel = hiltViewModel(),
    navigateTo: (Screen, Int) -> Unit
) {

    val foodRecipesState by viewModel.getFoodRecipesState.collectAsStateWithLifecycle()

    when (foodRecipesState) {
        is State.Success -> {
            HomeScreenView(foodRecipeList = (foodRecipesState as State.Success).result as List<FoodRecipeItemUi>) {
                navigateTo.invoke(Screen.Details, it)
            }
        }
        else -> {

        }
    }


}





