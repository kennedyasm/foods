package com.example.foods.domain.usecasesimpl

import com.example.foods.core.extensions.runIo
import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipesUseCaseImpl @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val schedulers: RxSchedulers
) : GetFoodRecipesUseCase {

    override fun getFoodRecipes(): Single<List<FoodRecipeItemDTO>> {
        return foodRecipesRepository.getFoodRecipes().runIo(schedulers)
    }
}
