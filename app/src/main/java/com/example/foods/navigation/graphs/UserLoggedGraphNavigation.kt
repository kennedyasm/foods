package com.example.foods.navigation.graphs

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foods.feature.location.FoodRecipeLocationMapScreen
import com.example.foods.features.details.FoodRecipeDetailsScreen
import com.example.foods.features.home.FoodRecipesHomeScreen
import com.example.foods.navigation.NavParamNames
import com.example.foods.navigation.Screen
import com.example.foods.common.openAppSetting
import com.mamabe.features.profile.ProfileScreen
import com.mamabe.features.record.VideoPreviewScreen
import com.mamabe.features.record.VideoRecordScreen

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.foodRecipeUserLoggedGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Screen.FoodRecipeListGraph.route,
        startDestination = Screen.RecipesList.route
    ) {

        composable(route = Screen.RecipesList.route) {
            FoodRecipesHomeScreen { id ->
                navController.navigate("${Screen.Details.route}/$id")
            }
        }

        composable(route = "${Screen.Details.route}/{${NavParamNames.FOOD_RECIPE_ID}}") {
            FoodRecipeDetailsScreen(navigateToLocation = { id ->
                navController.navigate("${Screen.MapDetails.route}/$id")
            }, navigateToRecord = {
                navController.navigate(Screen.FoodRecipeRecordVideo.route)
            })
        }

        composable(route = "${Screen.MapDetails.route}/{${NavParamNames.FOOD_RECIPE_ID}}") {
            FoodRecipeLocationMapScreen()
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen {
                navController.navigate(Screen.ProfilePicture.route)
            }
        }

        composable(route = Screen.FoodRecipeRecordVideo.route) {
            VideoRecordScreen(navigateToVidePreview = { uri ->
                navController.navigate("${Screen.FoodRecipeVideoPreview.route}/$uri")
            }, openSettings = {
                openAppSetting(context)
            })
        }

        composable(route = "${Screen.FoodRecipeVideoPreview.route}/{${NavParamNames.FOOD_RECIPE_VIDEO_URI}}") {
            VideoPreviewScreen(uri = it.arguments?.getString(NavParamNames.FOOD_RECIPE_VIDEO_URI) ?: "")
        }
    }
}
