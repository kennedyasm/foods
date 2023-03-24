package com.example.domain.models

data class FoodRecipeDetailsUi(
    val imageUrl: String,
    val description: String,
    val origin: String,
    val latitude: Double,
    val longitude: Double,
    val ingredients: List<String>,
    val preparation: List<String>,
)
