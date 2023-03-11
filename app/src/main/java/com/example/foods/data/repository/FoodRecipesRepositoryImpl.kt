package com.example.foods.data.repository

import com.example.foods.data.local.entities.FoodRecipeItemEntity
import com.example.foods.data.network.dto.FoodRecipesResponseDto
import com.example.foods.data.toFoodRecipeItemUiList
import com.example.foods.data.toFoodRecipeDetailsUi
import com.example.foods.data.toFoodRecipeItemEntityList
import com.example.foods.domain.local.FoodRecipesLocalDataSource
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.network.FoodRecipesNetworkDataSource
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FoodRecipesRepositoryImpl @Inject constructor(
    private val networkDataSource: FoodRecipesNetworkDataSource,
    private val localDataSource: FoodRecipesLocalDataSource
) : FoodRecipesRepository {

    override fun getFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return networkDataSource.getFoodRecipes()
            .flatMap(::insertFoodRecipesInDatabase)
            .map(FoodRecipesResponseDto::toFoodRecipeItemUiList)
    }

    override fun getFoodRecipeDetailsById(id: Int): Single<FoodRecipeDetailsUi> {
        return localDataSource.getFoodRecipeById(id)
            .map(FoodRecipeItemEntity::toFoodRecipeDetailsUi)
    }

    private fun insertFoodRecipesInDatabase(
        foodRecipesResponseDto: FoodRecipesResponseDto
    ): Single<FoodRecipesResponseDto> {
        return localDataSource.insertFoodRecipes(foodRecipesResponseDto.toFoodRecipeItemEntityList())
            .toSingle { foodRecipesResponseDto }
    }
}
