package com.example.foods.domain.models

data class FoodRecipeMapDetailUi(val id: Int, val name: String, val latitude: Double, val longitude: Double) {

    companion object {
        fun empty(): FoodRecipeMapDetailUi =  FoodRecipeMapDetailUi(-1, "",0.0, 0.0)
    }
}
