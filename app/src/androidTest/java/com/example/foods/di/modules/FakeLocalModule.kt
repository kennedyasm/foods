package com.example.foods.di.modules

import android.content.Context
import androidx.room.Room
import com.example.foods.data.local.FoodRecipesRoomDatabase
import com.example.foods.data.local.dao.FoodRecipesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FakeLocalModule {

    @Singleton
    @Provides
    fun provideFoodRecipesDatabase(context: Context): FoodRecipesRoomDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            FoodRecipesRoomDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideFoodRecipesDao(foodRecipesRoomDatabase: FoodRecipesRoomDatabase): FoodRecipesDao =
        foodRecipesRoomDatabase.foodRecipesDao()
}