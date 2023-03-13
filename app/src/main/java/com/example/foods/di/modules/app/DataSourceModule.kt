package com.example.foods.di.modules.app

import com.example.foods.data.local.datasource.FoodRecipesLocalDataSourceImpl
import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSourceImpl
import com.example.foods.data.local.datasource.FoodRecipesLocalDataSource
import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSource
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindFoodRecipesNetworkDataSource(
        foodRecipesNetworkDataSourceImpl: FoodRecipesNetworkDataSourceImpl
    ): FoodRecipesNetworkDataSource

    @Binds
    fun bindFoodRecipesLocalDatasource(
        foodRecipesLocalDataSourceImpl: FoodRecipesLocalDataSourceImpl
    ): FoodRecipesLocalDataSource
}
