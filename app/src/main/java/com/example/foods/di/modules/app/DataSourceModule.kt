package com.example.foods.di.modules.app

import com.example.foods.data.local.FoodRecipesLocalDataSourceImpl
import com.example.foods.data.network.FoodRecipesNetworkDataSourceImpl
import com.example.foods.domain.local.FoodRecipesLocalDataSource
import com.example.foods.domain.network.FoodRecipesNetworkDataSource
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
