package com.example.data.di

import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.local.datasource.FoodRecipesLocalDataSourceImpl
import com.example.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.data.network.datasource.FoodRecipesNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsFoodRecipesNetworkDataSource(
        impl: FoodRecipesNetworkDataSourceImpl
    ): FoodRecipesNetworkDataSource

    @Binds
    abstract fun bindsFoodRecipeLocalDataSource(
        impl: FoodRecipesLocalDataSourceImpl
    ): FoodRecipesLocalDataSource
}
