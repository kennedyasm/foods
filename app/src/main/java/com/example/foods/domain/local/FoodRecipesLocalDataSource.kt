package com.example.foods.domain.local

import com.example.foods.data.local.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FoodRecipesLocalDataSource {

    fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable
    fun getFoodRecipeById(id: Int): Single<FoodRecipeItemEntity>
}
