package com.example.foods.data.source.network.dto

import com.google.gson.annotations.SerializedName

data class FoodRecipesResponseDto(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("foodRecipes")
    val foodRecipes: List<FoodRecipeItemDto>
)
