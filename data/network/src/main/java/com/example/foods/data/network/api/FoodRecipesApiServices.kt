package com.example.foods.data.network.api

import com.example.foods.data.network.dto.FoodRecipesResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FoodRecipesApiServices {

    @GET(FOOD_RECIPES)
    fun getFoodRecipes(): Single<FoodRecipesResponseDto>

    companion object {
        private const val FOOD_RECIPES = "recipes"
    }
}
