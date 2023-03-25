package com.example.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.domain.State
import com.example.domain.models.FoodRecipeItemUi
import com.example.presentation.viewmodel.FoodRecipesViewModel

@Composable
fun FoodRecipesApp() {


    val navController = rememberNavController()
    //val homeViewModel: FoodRecipesViewModel = hiltViewModel()
    //val beers = remember { homeViewModel.getBeers() }.collectAsLazyPagingItems()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {

        composable(route = Screen.Home.route){
            //homeScreen(homeViewModel)
        }
        composable(route = Screen.Details.route){
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(viewModel: FoodRecipesViewModel) {
    
   //val uiState by viewModel.getFoodRecipesState.c

/*
    val foodRecipesState by viewModel.getFoodRecipesState.collectAsStateWithLifecycle()

    when(foodRecipesState) {
        is State.Success -> {
            HomeScreen(foodRecipeList =  foodRecipesState as List<FoodRecipeItemUi>)
        }
        else -> {

        }
    }
    */


}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(foodRecipeList: List<FoodRecipeItemUi>){


    val state = rememberLazyGridState()


    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .testTag("forYou:feed"),
        state = state,
    ) {
        items(foodRecipeList) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {


                Row(modifier = Modifier.padding(all = 8.dp)) {
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = " ${it.name}")
                }
            }
        }
    }
}



