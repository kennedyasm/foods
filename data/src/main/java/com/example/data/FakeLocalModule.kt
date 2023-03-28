package com.example.data

import android.content.Context
import androidx.room.Room
import com.example.data.di.RepositoryModule
import com.example.data.local.FoodRecipesRoomDatabase
import com.example.data.local.LocalModule
import com.example.data.local.dao.FoodRecipesDao
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

    private const val DB_NAME = "food_recipes_database"


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
