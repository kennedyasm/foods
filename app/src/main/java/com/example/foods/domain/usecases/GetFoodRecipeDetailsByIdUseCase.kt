package com.example.foods.domain.usecases

import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Single

class GetFoodRecipeDetailsByIdUseCase constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val schedulers: RxSchedulers
) {
    operator fun invoke(id: Int): Single<FoodRecipeDetailsUi> {
        return foodRecipesRepository.getFoodRecipeDetailsById(id)
            .subscribeOn(schedulers.io).observeOn(schedulers.mainThread)
    }
}
