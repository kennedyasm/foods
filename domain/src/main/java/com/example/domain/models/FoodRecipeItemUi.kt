package com.example.domain.models

data class FoodRecipeItemUi(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val ingredients: List<String>
) {
    companion object{
        fun FoodRecipeItemUi.isMatchingWithSearchQuery(query: String): Boolean =
            (name.contains(query, true) || ingredients.any { it.contains(query) })
    }
}
