package com.example.foods.core.errors

sealed class FoodRecipesError(override val message: String?) : Throwable(message) {
    data class NoFoodRecipeFounded(
        val errorMessage: String = NO_FOOD_RECIPE_FOUNDED_MESSAGE
    ) : FoodRecipesError(errorMessage)

    companion object {
        private const val NO_FOOD_RECIPE_FOUNDED_MESSAGE =
            "No se encontró más información acerca de esta receta"
    }
}
