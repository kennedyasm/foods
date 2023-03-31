package com.example.foods.data.source.network.datasource

import com.example.foods.data.source.network.dto.FoodRecipesResponseDto
import com.example.foods.data.source.network.api.FoodRecipesApiServices
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesNetworkDataSourceImpl @Inject constructor(
    private val foodRecipesApiServices: FoodRecipesApiServices
) : FoodRecipesNetworkDataSource {

    override fun getFoodRecipes(): Single<FoodRecipesResponseDto> {
        return foodRecipesApiServices.getFoodRecipes()
    }
}
