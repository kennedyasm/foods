package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.FoodRecipeItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FoodRecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(foodRecipeItemEntityList: List<FoodRecipeItemEntity>): Completable

    @Query("SELECT * FROM food_recipes")
    suspend fun getAll(): List<FoodRecipeItemEntity>

    @Query("SELECT * FROM food_recipes WHERE id=:id")
    suspend fun getById(id: Int): FoodRecipeItemEntity

    @Query("DELETE FROM food_recipes")
    fun delete(): Completable
}
