package com.example.data.network.datasource

import com.example.data.network.dto.FoodRecipesResponseDto
import io.reactivex.rxjava3.core.Single

interface FoodRecipesNetworkDataSource {
    fun getFoodRecipes(): Single<FoodRecipesResponseDto>
}
