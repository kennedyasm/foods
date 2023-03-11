package com.example.foods.data.network

import com.example.foods.domain.network.FoodRecipesNetworkDataSource
import com.example.foods.data.network.dto.FoodRecipesResponseDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesNetworkDataSourceImpl @Inject constructor(
    private val foodRecipesApiServices: FoodRecipesApiServices
) : FoodRecipesNetworkDataSource {

    override fun getFoodRecipes(): Single<FoodRecipesResponseDto> {
        return foodRecipesApiServices.getFoodRecipes()
    }
}
