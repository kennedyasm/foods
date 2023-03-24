package com.example.domain.usecases

import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipesUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    operator fun invoke(): Single<List<FoodRecipeItemUi>> {
        return foodRecipesRepository.getFoodRecipes()
    }
}
