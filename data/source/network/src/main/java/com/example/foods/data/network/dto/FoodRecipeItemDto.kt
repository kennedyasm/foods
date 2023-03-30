package com.example.foods.data.network.dto

import com.google.gson.annotations.SerializedName

data class FoodRecipeItemDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("preparation")
    val preparation: List<String>,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
