package com.example.data.di

import com.example.data.repository.FoodRecipesRepositoryImpl
import com.example.domain.repository.FoodRecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsFoodRecipesRepository(
        impl: FoodRecipesRepositoryImpl
    ): FoodRecipesRepository
}