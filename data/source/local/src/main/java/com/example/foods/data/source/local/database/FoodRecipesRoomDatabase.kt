package com.example.foods.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foods.data.source.local.database.FoodRecipesRoomDatabase.Companion.DATA_BASE_VERSION
import com.example.foods.data.source.local.database.dao.FoodRecipesDao
import com.example.foods.data.source.local.database.entities.FoodRecipeItemEntity

@Database(entities = [FoodRecipeItemEntity::class], version = DATA_BASE_VERSION)
abstract class FoodRecipesRoomDatabase: RoomDatabase() {

    abstract fun foodRecipesDao(): FoodRecipesDao

    companion object {
        const val DATA_BASE_VERSION = 1
    }
}
