package com.example.foods.domain.usecases

import com.example.foods.domain.repository.FoodRecipesRepository
import javax.inject.Inject

class GetFoodRecipeMapDetailByIdUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    suspend operator fun invoke(id: Int) = foodRecipesRepository.getFoodRecipeMapDetailById(id)
}