package com.example.foods.domain.doubles

import com.example.foods.domain.models.FoodRecipeItemUi

fun provideFoodRecipeItemUiList() =
    listOf(provideFoodRecipeItemUiBowlThail(), provideFoodRecipeItemUiCaldoDePiedra())

fun provideFoodRecipeItemUiBowlThail() =
    FoodRecipeItemUi(
        1,
        "BOWL THAI",
        "https://i0.wp.com/bestofvegan.com/wp-content/uploads/2020/12/Thai-Noodle-Bowl.jpg",
        emptyList(),
    )

fun provideFoodRecipeItemUiCaldoDePiedra() =
    FoodRecipeItemUi(
        2,
        "CALDO DE PIEDRA",
        "https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg",
        caldoDePiedraIngredients,
    )
