package com.example.foods.doubles

import com.example.foods.data.toFoodRecipeItemEntity
import com.example.foods.data.toFoodRecipeItemEntityList

fun provideFoodRecipeItemEntityList() = provideFoodRecipesResponseDto().toFoodRecipeItemEntityList()

fun provideCaldoDePiedraFoodRecipeItemEntity() =
    provideCaldoDePiedraFoodRecipeItemDto().toFoodRecipeItemEntity()
