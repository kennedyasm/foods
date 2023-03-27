package com.example.domain.models

data class FoodRecipeDetailsUi(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val origin: String,
    val ingredients: List<String>,
    val preparation: List<String>,
)
