package com.example.foods.domain.repository

import com.example.foods.domain.dto.FoodRecipeItemDTO
import io.reactivex.rxjava3.core.Single

interface FoodRecipesRepository {

    fun getFoodRecipes(): Single<List<FoodRecipeItemDTO>>

    fun getFoodRecipeById(id: Int): Single<FoodRecipeItemDTO>
}
