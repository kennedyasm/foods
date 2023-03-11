package com.example.foods.di.modules.app

import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCasesModule {

    @Provides
    fun provideGetFoodRecipesUseCase(
        foodRecipesRepository: FoodRecipesRepository,
        rxSchedulers: RxSchedulers
    ) = GetFoodRecipesUseCase(foodRecipesRepository, rxSchedulers)

    @Provides
    fun provideGetFoodRecipeByIdUseCase(
        foodRecipesRepository: FoodRecipesRepository,
        rxSchedulers: RxSchedulers
    ) = GetFoodRecipeDetailsByIdUseCase(foodRecipesRepository, rxSchedulers)
}
