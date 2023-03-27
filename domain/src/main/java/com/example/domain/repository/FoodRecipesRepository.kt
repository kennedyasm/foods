package com.example.domain.repository

import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.models.FoodRecipeItemUi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface FoodRecipesRepository {
    fun getFoodRecipes(): Single<List<FoodRecipeItemUi>>
    suspend fun getFoodRecipeDetailsById(id: Int): FoodRecipeDetailsUi
    fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>>
    fun deleteFoodRecipes(): Completable
}
