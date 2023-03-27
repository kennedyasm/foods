package com.example.data.local.datasource

import com.example.data.local.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FoodRecipesLocalDataSource {
    fun insertFoodRecipes(foodRecipes: List<FoodRecipeItemEntity>): Completable
    suspend fun getFoodRecipes(): List<FoodRecipeItemEntity>
    suspend fun getFoodRecipeById(id: Int): FoodRecipeItemEntity
    fun deleteFoodRecipes(): Completable
}
