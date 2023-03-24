package com.example.data.local.di

import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.local.datasource.FoodRecipesLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsFoodRecipesLocalDataSource(
        impl: FoodRecipesLocalDataSourceImpl
    ): FoodRecipesLocalDataSource
}
