package com.example.foods.domain.network

import com.example.foods.data.network.dto.FoodRecipesResponseDto
import io.reactivex.rxjava3.core.Single

interface FoodRecipesNetworkDataSource {
    fun getFoodRecipes(): Single<FoodRecipesResponseDto>
}
