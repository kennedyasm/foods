package com.example.data.network.datasource

import com.example.data.network.FoodRecipesApiServices
import com.example.data.network.dto.FoodRecipesResponseDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesNetworkDataSourceImpl @Inject constructor(
    private val foodRecipesApiServices: FoodRecipesApiServices
) : FoodRecipesNetworkDataSource {

    override fun getFoodRecipes(): Single<FoodRecipesResponseDto> {
        return foodRecipesApiServices.getFoodRecipes()
    }
}
