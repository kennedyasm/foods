package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.FoodRecipesRoomDatabase.Companion.DATA_BASE_VERSION
import com.example.data.local.dao.FoodRecipesDao
import com.example.data.local.entities.FoodRecipeItemEntity

@Database(entities = [FoodRecipeItemEntity::class], version = DATA_BASE_VERSION)
abstract class FoodRecipesRoomDatabase: RoomDatabase() {

    abstract fun foodRecipesDao(): FoodRecipesDao

    companion object {
        const val DATA_BASE_VERSION = 1
    }
}
