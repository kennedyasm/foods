package com.example.foods.data.local.datasource

import com.example.foods.data.local.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FoodRecipesLocalDataSource {
    fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable
    suspend fun getFoodRecipes(): List<FoodRecipeItemEntity>
    fun deleteFoodRecipes(): Completable
    fun getFoodRecipeById(id: Int): Single<FoodRecipeItemEntity>
}
