package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FoodRecipesFragmentScoped
import com.example.foods.presentation.fragments.FoodRecipeDetailsFragment
import com.example.foods.presentation.fragments.FoodRecipeOriginMapFragment
import com.example.foods.presentation.fragments.FoodRecipesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModules {

    @FoodRecipesFragmentScoped
    @ContributesAndroidInjector(modules = [FoodFragmentFeaturesModule::class])
    abstract fun foodRecipesFragment(): FoodRecipesFragment

    @ContributesAndroidInjector
    abstract fun foodRecipeDetailsFragment(): FoodRecipeDetailsFragment

    @ContributesAndroidInjector
    abstract fun foodRecipeOriginMapFragment(): FoodRecipeOriginMapFragment
}
