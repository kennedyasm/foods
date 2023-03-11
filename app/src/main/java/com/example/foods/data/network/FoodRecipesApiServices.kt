package com.example.foods.data.network

import com.example.foods.domain.network.FoodRecipesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FoodRecipesApiServices {

    @GET(FOOD_RECIPES)
    fun getFoodRecipes(): Single<FoodRecipesResponse>

    companion object {
        private const val FOOD_RECIPES = "foods/recipes"
    }
}
