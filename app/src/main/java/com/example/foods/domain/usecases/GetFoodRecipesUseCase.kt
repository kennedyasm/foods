package com.example.foods.domain.usecases

import com.example.foods.core.extensions.runIo
import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single

class GetFoodRecipesUseCase constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val schedulers: RxSchedulers
) {
    operator fun invoke(): Single<List<FoodRecipeItemUi>> {
        return foodRecipesRepository.getFoodRecipes().runIo(schedulers)
    }
}
