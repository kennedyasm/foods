package com.example.foods.data.test

import android.content.Context
import androidx.room.Room
import com.example.foods.data.local.database.FoodRecipesRoomDatabase
import com.example.foods.data.local.database.dao.FoodRecipesDao
import com.example.foods.data.local.di.LocalModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LocalModule::class]
)
object FakeLocalModule {

    @Singleton
    @Provides
    fun provideFoodRecipesDatabase(@ApplicationContext context: Context): FoodRecipesRoomDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            FoodRecipesRoomDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideFoodRecipesDao(foodRecipesRoomDatabase: FoodRecipesRoomDatabase): FoodRecipesDao =
        foodRecipesRoomDatabase.foodRecipesDao()
}
