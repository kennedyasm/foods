package com.example.foods.data.repository

import com.example.foods.data.local.datasource.FoodRecipesLocalDataSource
import com.example.foods.data.local.entities.FoodRecipeItemEntity
import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.foods.data.network.dto.FoodRecipesResponseDto
import com.example.foods.data.toFoodRecipeDetailsUi
import com.example.foods.data.toFoodRecipeItemEntityList
import com.example.foods.data.toFoodRecipeItemUi
import com.example.foods.data.toFoodRecipeItemUiList
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodRecipesRepositoryImpl @Inject constructor(
    private val networkDataSource: FoodRecipesNetworkDataSource,
    private val localDataSource: FoodRecipesLocalDataSource
) : FoodRecipesRepository {

    override fun getFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return localDataSource.deleteFoodRecipes().andThen(fetchFoodRecipes())
    }

    override fun getFoodRecipeDetailsById(id: Int): Single<FoodRecipeDetailsUi> {
        return localDataSource.getFoodRecipeById(id).map { it.toFoodRecipeDetailsUi() }
    }

    override fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>> {
        return flow {
            val data = localDataSource.getFoodRecipes()
                .filter { it.isMatchingWithSearchQuery(query) }
                .map(FoodRecipeItemEntity::toFoodRecipeItemUi)
            emit(data)
        }
    }

    override fun deleteFoodRecipes(): Completable {
        return localDataSource.deleteFoodRecipes()
    }

    private fun fetchFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return networkDataSource.getFoodRecipes().flatMap(::insertFoodRecipesInDatabase)
            .map(FoodRecipesResponseDto::toFoodRecipeItemUiList)

    }

    private fun insertFoodRecipesInDatabase(
        foodRecipesResponseDto: FoodRecipesResponseDto
    ): Single<FoodRecipesResponseDto> {
        return localDataSource.insertFoodRecipes(foodRecipesResponseDto.toFoodRecipeItemEntityList())
            .toSingle { foodRecipesResponseDto }
    }

    companion object {
        private fun FoodRecipeItemEntity.isMatchingWithSearchQuery(query: String): Boolean =
            (name.contains(query, true) || ingredients.any { it.contains(query) })
    }
}
