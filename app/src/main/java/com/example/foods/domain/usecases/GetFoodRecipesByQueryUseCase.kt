package com.example.foods.domain.usecases

import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.repository.FoodRecipesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetFoodRecipesByQueryUseCase(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(query: String): Flow<List<FoodRecipeItemUi>> =
        foodRecipesRepository.getFoodRecipesByQuery(query).flowOn(dispatcher)

}