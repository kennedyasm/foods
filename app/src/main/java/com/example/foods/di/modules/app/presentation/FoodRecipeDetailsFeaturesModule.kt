package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FoodRecipeDetailsFragmentScoped
import com.example.foods.presentation.adapters.RecipePreparationIngredientsAdapter
import com.example.foods.presentation.fragments.FoodRecipeDetailsFragment
import dagger.Module
import dagger.Provides

@Module
object FoodRecipeDetailsFeaturesModule {

    @FoodRecipeDetailsFragmentScoped
    @Provides
    fun providesFoodRecipeDetailsAdapter(foodRecipeDetailsFragment: FoodRecipeDetailsFragment) =
        RecipePreparationIngredientsAdapter(foodRecipeDetailsFragment.requireActivity())
}
