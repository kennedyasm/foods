package com.example.foods.di.modules.app.presentation

import com.example.foods.di.FragmentScoped
import com.example.foods.presentation.fragments.FoodRecipeDetailsFragment
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
    abstract fun foodRecipeDetailsFragment(): FoodRecipeDetailsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun foodRecipeOriginMapFragment(): FoodRecipeOriginMapFragment
}
