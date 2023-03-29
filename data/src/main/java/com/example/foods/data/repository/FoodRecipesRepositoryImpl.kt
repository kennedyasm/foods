package com.example.foods.data.repository

import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.foods.data.network.dto.FoodRecipesResponseDto
import com.example.foods.data.toFoodRecipeDetailsUi
import com.example.foods.data.toFoodRecipeItemEntityList
import com.example.foods.data.toFoodRecipeItemUi
import com.example.foods.data.toFoodRecipeItemUiList
import com.example.foods.data.toFoodRecipeMapDetailUi
import com.example.foods.data.local.datasource.FoodRecipesLocalDataSource
import com.example.foods.data.local.database.entities.FoodRecipeItemEntity
import com.example.foods.data.scheduler.RxSchedulers
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.models.FoodRecipeMapDetailUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipesRepositoryImpl @Inject constructor(
    private val networkDataSource: FoodRecipesNetworkDataSource,
    private val localDataSource: FoodRecipesLocalDataSource,
    private val schedulers: RxSchedulers,
    private val dispatcherIO: CoroutineDispatcher
) : FoodRecipesRepository {

    override fun getFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return localDataSource.deleteFoodRecipes().andThen(fetchFoodRecipes())
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
    }

    override suspend fun getFoodRecipeDetailsById(id: Int): FoodRecipeDetailsUi = withContext(dispatcherIO){

        localDataSource.getFoodRecipeById(id).toFoodRecipeDetailsUi()

    }

    override fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>> = flow {
        val data = localDataSource.getFoodRecipes()
            .filter { it.isMatchingWithSearchQuery(query) }
            .map(FoodRecipeItemEntity::toFoodRecipeItemUi)
        emit(data)
    }.flowOn(dispatcherIO)

    override fun deleteFoodRecipes(): Completable {
        return localDataSource.deleteFoodRecipes()
    }

    private fun fetchFoodRecipes(): Single<List<FoodRecipeItemUi>> {
        return networkDataSource.getFoodRecipes().flatMap(::insertFoodRecipesInDatabase)
            .map(FoodRecipesResponseDto::toFoodRecipeItemUiList)

    }

    override suspend fun getFoodRecipeMapDetailById(id: Int): FoodRecipeMapDetailUi =
        withContext(dispatcherIO) {

            localDataSource.getFoodRecipeById(id).toFoodRecipeMapDetailUi()
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
