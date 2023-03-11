package com.example.foods.domain.usecasesimpl

import com.example.foods.core.extensions.runIo
import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.domain.usecases.GetFoodRecipeByIdUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFoodRecipeByIdUseCaseImpl @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val schedulers: RxSchedulers
) : GetFoodRecipeByIdUseCase {

    override fun getFoodRecipeById(id: Int): Single<FoodRecipeItemDTO> {
        return foodRecipesRepository.getFoodRecipeById(id).runIo(schedulers)
    }
}
