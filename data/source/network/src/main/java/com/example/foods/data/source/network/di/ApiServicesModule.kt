package com.example.foods.data.source.network.di

import com.example.foods.data.source.network.api.FoodRecipesApiServices
import com.example.foods.data.source.network.retrofit.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Provides
    fun provideFoodRecipesApiServices(retrofitHelper: RetrofitHelper): FoodRecipesApiServices {
        return retrofitHelper.createService(FoodRecipesApiServices::class.java)
    }
}
