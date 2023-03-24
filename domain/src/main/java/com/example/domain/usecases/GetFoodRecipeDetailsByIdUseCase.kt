package com.example.domain.usecases

import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipeDetailsByIdUseCase @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) {
    operator fun invoke(id: Int): Single<FoodRecipeDetailsUi> {
        return foodRecipesRepository.getFoodRecipeDetailsById(id)
    }
}
