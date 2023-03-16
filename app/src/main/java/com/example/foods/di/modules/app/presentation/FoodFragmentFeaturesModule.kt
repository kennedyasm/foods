package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FoodRecipesFragmentScoped
import com.example.foods.presentation.adapters.FoodRecipesAdapter
import dagger.Module
import dagger.Provides

@Module
object FoodFragmentFeaturesModule {

    @FoodRecipesFragmentScoped
    @Provides
    fun providesFoodRecipesAdapter() = FoodRecipesAdapter()
}
