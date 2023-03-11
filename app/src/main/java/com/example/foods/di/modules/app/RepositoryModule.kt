package com.example.foods.di.modules.app

import com.example.foods.data.repository.FoodRecipesRepositoryImpl
import com.example.foods.domain.repository.FoodRecipesRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindFoodRecipesRepository(
        foodRecipesRepository: FoodRecipesRepositoryImpl
    ): FoodRecipesRepository
}
