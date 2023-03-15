package com.example.foods.doubles

import com.example.foods.data.toFoodRecipeItemUi
import com.example.foods.data.toFoodRecipeItemUiList

fun provideFoodRecipeItemUiList() =
    provideFoodRecipesResponseDto().toFoodRecipeItemUiList()

fun provideFoodRecipeItemUiCaldoDePiedra() =
    provideCaldoDePiedraFoodRecipeItemDto().toFoodRecipeItemUi()