package com.example.foods.di.modules.app.presentation

import androidx.lifecycle.ViewModel
import com.example.foods.core.factory.ViewModelKey
import com.example.foods.di.MainActivityScoped
import com.example.foods.presentation.viewmodel.FoodRecipeDetailsViewModel
import com.example.foods.presentation.viewmodel.FoodRecipesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindsModule {

    @MainActivityScoped
    @Binds
    @IntoMap
    @ViewModelKey(FoodRecipesViewModel::class)
    abstract fun bindFoodRecipesViewModel(viewModel: FoodRecipesViewModel): ViewModel

    @MainActivityScoped
    @Binds
    @IntoMap
    @ViewModelKey(FoodRecipeDetailsViewModel::class)
    abstract fun bindFoodRecipeDetailsViewModel(viewModel: FoodRecipeDetailsViewModel): ViewModel
}
