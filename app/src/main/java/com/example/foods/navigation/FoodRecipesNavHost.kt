package com.example.foods.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foods.feature.location.FoodRecipeLocationMapScreen
import com.example.foods.features.details.FoodRecipeDetailsScreen
import com.example.foods.features.home.FoodRecipesHomeScreen
import com.example.foods.navigation.NavParamNames.FOOD_RECIPE_ID
import com.example.foods.navigation.NavParamNames.FOOD_RECIPE_VIDEO_URI
import com.mamabe.features.profile.ProfileScreen
import com.mamabe.features.record.VideoPreviewScreen
import com.mamabe.features.record.VideoRecordScreen


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FoodRecipesNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val items = listOf(
        Screen.RecipesList,
        Screen.Profile,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "RECIPE FOODS",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                })
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            when (screen.route) {
                                Screen.Profile.route -> Icon(
                                    Icons.Filled.AccountCircle,
                                    contentDescription = null
                                )
                                Screen.RecipesList.route -> Icon(
                                    Icons.Filled.Home,
                                    contentDescription = null
                                )
                            }

                        },
                        label = { Text(screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.RecipesList.route,
            Modifier.padding(innerPadding)
        ) {

            composable(route = Screen.RecipesList.route) {
                FoodRecipesHomeScreen { id ->
                    navController.navigate("${Screen.Details.route}/$id")
                }
            }

            composable(route = "${Screen.Details.route}/{$FOOD_RECIPE_ID}") {
                FoodRecipeDetailsScreen(navigateToLocation = { id ->
                    navController.navigate("${Screen.MapDetails.route}/$id")
                }, navigateToRecord = {
                    navController.navigate(Screen.FoodRecipeRecordVideo.route)
                })
            }

            composable(route = "${Screen.MapDetails.route}/{$FOOD_RECIPE_ID}") {
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

            composable(route = "${Screen.FoodRecipeVideoPreview.route}/{$FOOD_RECIPE_VIDEO_URI}") {
                VideoPreviewScreen(uri = it.arguments?.getString(FOOD_RECIPE_VIDEO_URI) ?: "")
            }
        }
    }
}

fun openAppSetting(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    startActivity(context, intent, null)
}
