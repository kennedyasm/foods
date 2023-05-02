package com.example.foods.features.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.foods.domain.models.FoodRecipeItemUi

@Preview
@Composable
fun HomePreview() {
    HomeScreenView(
        foodRecipeList = recipeFoods(),
        searchText = "Hola",
        isSearching = true,
        hasSearchFocus = true,
        onValueChange = {
        },
        onClearFocus = {
        },
        onSearchViewHasFocus = {
        },
        onFoodRecipeItemClick = {
        }
    )
}

fun recipeFoods() = listOf(
    FoodRecipeItemUi(
        1,
        "BOWL THAI",
        "https://i0.wp.com/bestofvegan.com/wp-content/uploads/2020/12/Thai-Noodle-Bowl.jpg",
        emptyList()
    ),
    FoodRecipeItemUi(
        2,
        "CALDO DE PIEDRA",
        "https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg",
        emptyList()
    )
)
