package com.example.foods.domain.usecases

import com.example.foods.domain.dto.FoodRecipeItemDTO
import io.reactivex.rxjava3.core.Single

interface GetFoodRecipeByIdUseCase {

    fun getFoodRecipeById(id: Int): Single<FoodRecipeItemDTO>
}
