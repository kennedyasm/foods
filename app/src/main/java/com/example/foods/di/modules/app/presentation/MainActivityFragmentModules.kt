package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FragmentScoped
import com.example.foods.presentation.fragments.FoodRecipeDetails
import com.example.foods.presentation.fragments.FoodRecipeOriginMapFragment
import com.example.foods.presentation.fragments.FoodRecipesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModules {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodRecipesFragment(): FoodRecipesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodRecipeDetailsFragment(): FoodRecipeDetails

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodRecipeOriginMapFragment(): FoodRecipeOriginMapFragment
}
