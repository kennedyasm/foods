package com.example.foods.data.source.local.datasource

import com.example.foods.data.source.local.database.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable

interface FoodRecipesLocalDataSource {
    fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable
    suspend fun getFoodRecipes(): List<FoodRecipeItemEntity>
    suspend fun getFoodRecipeById(id: Int): FoodRecipeItemEntity
    fun deleteFoodRecipes(): Completable
}
