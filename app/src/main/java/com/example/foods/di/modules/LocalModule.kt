package com.example.foods.di.modules

import android.content.Context
import androidx.room.Room
import com.example.foods.data.local.dao.FoodRecipesDao
import com.example.foods.data.local.FoodRecipesRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {

    private const val DB_NAME = "food_recipes_database"

    @Singleton
    @Provides
    fun providesFoodRecipesRoomDatabase(context: Context): FoodRecipesRoomDatabase =
        Room.databaseBuilder(context, FoodRecipesRoomDatabase::class.java, DB_NAME).build()

    @Provides
    fun provideFoodRecipesDao(foodRecipesRoomDatabase: FoodRecipesRoomDatabase): FoodRecipesDao =
        foodRecipesRoomDatabase.foodRecipesDao()
}
