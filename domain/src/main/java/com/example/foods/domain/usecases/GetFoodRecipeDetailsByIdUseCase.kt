package com.example.foods.domain.usecases

import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.repository.FoodRecipesRepository
import javax.inject.Inject

class GetFoodRecipeDetailsByIdUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    suspend operator fun invoke(id: Int): FoodRecipeDetailsUi {
        return foodRecipesRepository.getFoodRecipeDetailsById(id)
    }
}
