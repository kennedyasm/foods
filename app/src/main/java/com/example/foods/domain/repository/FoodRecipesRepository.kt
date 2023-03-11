package com.example.foods.domain.repository

import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import io.reactivex.rxjava3.core.Single

interface FoodRecipesRepository {

    fun getFoodRecipes(): Single<List<FoodRecipeItemUi>>

    fun getFoodRecipeDetailsById(id: Int): Single<FoodRecipeDetailsUi>
}
