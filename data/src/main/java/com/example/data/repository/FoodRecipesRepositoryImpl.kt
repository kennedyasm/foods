package com.example.data.repository

import com.example.common.async.rx.RxSchedulers
import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.local.entities.FoodRecipeItemEntity
import com.example.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.data.network.dto.FoodRecipesResponseDto
import com.example.data.toFoodRecipeDetailsUi
import com.example.data.toFoodRecipeItemEntityList
import com.example.data.toFoodRecipeItemUi
import com.example.data.toFoodRecipeItemUiList
import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun getFoodRecipeDetailsById(id: Int): Single<FoodRecipeDetailsUi> {
        return localDataSource.getFoodRecipeById(id).map { it.toFoodRecipeDetailsUi() }
            .subscribeOn(schedulers.io).observeOn(schedulers.mainThread)
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
