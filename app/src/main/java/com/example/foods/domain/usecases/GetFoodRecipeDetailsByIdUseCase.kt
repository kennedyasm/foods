package com.example.foods.domain.usecases

import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single

class GetFoodRecipeDetailsByIdUseCase(private val foodRecipesRepository: FoodRecipesRepository) {
    operator fun invoke(id: Int): Single<FoodRecipeDetailsUi> =
        foodRecipesRepository.getFoodRecipeDetailsById(id)
}
