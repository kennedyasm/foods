package com.example.foods.di.modules.app

import com.example.foods.domain.usecases.GetFoodRecipeByIdUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import com.example.foods.domain.usecasesimpl.GetFoodRecipeByIdUseCaseImpl
import com.example.foods.domain.usecasesimpl.GetFoodRecipesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {

    @Binds
    fun bindGetFoodRecipesUseCase(
        getFoodRecipesUseCaseImpl: GetFoodRecipesUseCaseImpl
    ): GetFoodRecipesUseCase

    @Binds
    fun bindGetFoodRecipeByIdUseCase(
        getFoodRecipeByIdUseCaseImpl: GetFoodRecipeByIdUseCaseImpl
    ): GetFoodRecipeByIdUseCase
}
