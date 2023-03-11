package com.example.foods.di.modules.app

import com.example.foods.core.network.retrofit.RetrofitHelper
import com.example.foods.data.network.FoodRecipesApiServices
import dagger.Module
import dagger.Provides

@Module
object ApiServicesModule {

    @Provides
    fun provideFoodRecipesApiServices(retrofitHelper: RetrofitHelper): FoodRecipesApiServices {
        return retrofitHelper.createService(FoodRecipesApiServices::class.java)
    }
}
