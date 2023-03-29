package com.example.foods.domain.usecases

import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipesUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    operator fun invoke(): Single<List<FoodRecipeItemUi>> {
        return foodRecipesRepository.getFoodRecipes()
    }
}
