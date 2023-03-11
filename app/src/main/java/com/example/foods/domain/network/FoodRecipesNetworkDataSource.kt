package com.example.foods.domain.network

import io.reactivex.rxjava3.core.Single

interface FoodRecipesNetworkDataSource {

    fun getFoodRecipes(): Single<FoodRecipesResponse>
}
