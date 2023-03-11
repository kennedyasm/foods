package com.example.foods.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.foods.data.local.converters.StringListTypeConverters

@Entity("food_recipes")
data class FoodRecipeItemEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    @field:TypeConverters(StringListTypeConverters::class)
    var ingredients: List<String> = listOf(),
    @field:TypeConverters(StringListTypeConverters::class)
    var preparation: List<String> = listOf(),
    @ColumnInfo("image_url")
    var imageUrl: String = "",
    var origin: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)
