package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FoodRecipesFragmentScoped
import com.example.foods.presentation.adapters.FoodRecipesAdapter
import dagger.Module
import dagger.Provides

@Module
object FoodRecipesFragmentFeaturesModule {

    @FoodRecipesFragmentScoped
    @Provides
    fun providesFoodRecipesAdapter() = FoodRecipesAdapter()
}
