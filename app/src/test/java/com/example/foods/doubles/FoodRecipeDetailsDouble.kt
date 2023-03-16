package com.example.foods.doubles

import com.example.foods.data.toFoodRecipeDetailsUi
import com.example.foods.data.toFoodRecipeItemEntity

fun provideFoodRecipeDetails() =
    provideCaldoDePiedraFoodRecipeItemDto().toFoodRecipeItemEntity().toFoodRecipeDetailsUi()
