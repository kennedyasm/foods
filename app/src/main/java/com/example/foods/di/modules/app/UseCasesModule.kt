package com.example.foods.di.modules.app

import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import com.example.foods.domain.usecases.GetFoodRecipesByQueryUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCasesModule {

    @Provides
    fun provideGetFoodRecipesUseCase(foodRecipesRepository: FoodRecipesRepository) =
        GetFoodRecipesUseCase(foodRecipesRepository)

    @Provides
    fun provideGetFoodRecipeByIdUseCase(foodRecipesRepository: FoodRecipesRepository) =
        GetFoodRecipeDetailsByIdUseCase(foodRecipesRepository)

    @Provides
    fun provideGetFoodRecipesByQueryUseCase(foodRecipesRepository: FoodRecipesRepository) =
        GetFoodRecipesByQueryUseCase(foodRecipesRepository)
}
