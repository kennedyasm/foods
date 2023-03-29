package com.example.foods.data.network.di

import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsFoodNetworkDataSource(
        impl: FoodRecipesNetworkDataSourceImpl
    ): FoodRecipesNetworkDataSource
}
