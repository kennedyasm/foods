package com.example.domain.usecases

import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.repository.FoodRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodRecipesByQueryUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    operator fun invoke(query: String): Flow<List<FoodRecipeItemUi>> =
        foodRecipesRepository.getFoodRecipesByQuery(query)
}
