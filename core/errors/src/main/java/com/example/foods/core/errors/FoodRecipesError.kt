package com.example.foods.core.errors

sealed class FoodRecipesError(override val message: String?) : Throwable(message) {
    data class NoFoodRecipeFounded(
        val errorMessage: String = "No se encontró más información acerca de esta receta"
    ) : FoodRecipesError(errorMessage)
}