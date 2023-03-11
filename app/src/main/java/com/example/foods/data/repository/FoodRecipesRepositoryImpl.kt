package com.example.foods.data.repository

import com.example.foods.common.extensions.toFoodRecipeItemDTOList
import com.example.foods.common.extensions.toFoodRecipeItemEntityList
import com.example.foods.common.extensions.toFoodRecipeItemResponse
import com.example.foods.data.local.entities.FoodRecipeItemEntity
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.domain.local.FoodRecipesLocalDataSource
import com.example.foods.domain.network.FoodRecipesNetworkDataSource
import com.example.foods.domain.network.FoodRecipesResponse
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesRepositoryImpl @Inject constructor(
    private val networkDataSource: FoodRecipesNetworkDataSource,
    private val localDataSource: FoodRecipesLocalDataSource
) : FoodRecipesRepository {

    override fun getFoodRecipes(): Single<List<FoodRecipeItemDTO>> {
        return networkDataSource.getFoodRecipes()
            .flatMap(::insertFoodRecipesInDatabase)
            .map(FoodRecipesResponse::toFoodRecipeItemDTOList)
    }

    override fun getFoodRecipeById(id: Int): Single<FoodRecipeItemDTO> {
        return localDataSource.getFoodRecipeById(id).map(FoodRecipeItemEntity::toFoodRecipeItemResponse)
    }

    private fun insertFoodRecipesInDatabase(
        foodRecipesResponse: FoodRecipesResponse
    ): Single<FoodRecipesResponse> {
        return localDataSource.insertFoodRecipes(foodRecipesResponse.toFoodRecipeItemEntityList())
            .toSingle { foodRecipesResponse }
    }
}
