package com.example.data.network

import com.example.data.network.retrofit.RetrofitHelper
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
