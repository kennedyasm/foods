package com.example.domain.models

data class FoodRecipeMapDetailUi(val id: Int, val name: String, val latitude: Double, val longitude: Double) {

    fun isEmpty() = id == -1

    companion object {
        fun empty(): FoodRecipeMapDetailUi =  FoodRecipeMapDetailUi(-1, "",0.0, 0.0)
    }
}