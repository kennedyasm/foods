package com.example.foods.domain.usecases

import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.repository.FoodRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodRecipesByQueryUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    operator fun invoke(query: String): Flow<List<FoodRecipeItemUi>> =
        foodRecipesRepository.getFoodRecipesByQuery(query)
}
