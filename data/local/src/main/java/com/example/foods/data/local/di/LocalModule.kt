package com.example.foods.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.foods.data.local.database.FoodRecipesRoomDatabase
import com.example.foods.data.local.database.dao.FoodRecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DB_NAME = "food_recipes_database"

    @Singleton
    @Provides
    fun providesFoodRecipesRoomDatabase(@ApplicationContext context: Context): FoodRecipesRoomDatabase =
        Room.databaseBuilder(context, FoodRecipesRoomDatabase::class.java, DB_NAME).build()

    @Provides
    fun provideFoodRecipesDao(foodRecipesRoomDatabase: FoodRecipesRoomDatabase): FoodRecipesDao =
        foodRecipesRoomDatabase.foodRecipesDao()
}
