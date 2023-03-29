package com.example.foods.domain.repository

import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.models.FoodRecipeMapDetailUi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface FoodRecipesRepository {
    fun getFoodRecipes(): Single<List<FoodRecipeItemUi>>
    suspend fun getFoodRecipeDetailsById(id: Int): FoodRecipeDetailsUi
    fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>>
    fun deleteFoodRecipes(): Completable
    suspend fun getFoodRecipeMapDetailById(id: Int): FoodRecipeMapDetailUi
}
