package com.example.foods.core.testing.doubles

import com.example.foods.domain.models.FoodRecipeItemUi

fun provideFoodRecipeItemUiList() =
    listOf(provideFoodRecipeItemUiBowlThail(), provideFoodRecipeItemUiCaldoDePiedra())

fun provideFoodRecipeItemUiBowlThail() =
    FoodRecipeItemUi(
        1,
        "BOWL THAI",
        "https://i0.wp.com/bestofvegan.com/wp-content/uploads/2020/12/Thai-Noodle-Bowl.jpg",
        bowlThaiIngredients
    )

fun provideFoodRecipeItemUiCaldoDePiedra() =
    FoodRecipeItemUi(
        2,
        "CALDO DE PIEDRA",
        "https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg",
        caldoDePiedraIngredients
    )

val bowlThaiIngredients = listOf(
    "1 pechuga de pollo",
    "1/4 tazas de Conserva de fresa con chía McCormick® Balance, para marinar",
    "2 cucharadas de soya, para marinar",
    "1 cucharada de jengibre, finamente picado, para marinar",
    "1 cucharada de ajo, finamente picado, para marinar",
    "3 rebanadas de piña, para marinar",
    "2 tazas de arroz para sushi , cocido",
    "1 cucharada de aceite de ajonjolí, para el arroz",
    "1 cucharada de ajonjolí negro, para el arroz",
    "1 cucharada de soya, para el arroz",
    "1/4 tazas de tallo de cebolla cambray, para el arroz",
    "1 taza de germen de soya, para acompañar",
    "1/2 tazas de col morada, en tiritas, para acompañar",
    "1/2 tazas de edamame, para acompañar",
    "1/2 tazas de kale, frito",
    "al gusto de cilantro",
    "al gusto de limón",
    "al gusto de hojuela de chile, para decorar"
)

val caldoDePiedraIngredients = listOf(
    "2 cucharadas de aceite vegetal, para el fondo de camarón",
    "2 tazas de cabeza de camarón , para el fondo de camarón",
    "6 tazas de agua, para el fondo de camarón",
    "1/4 tazas de epazote, para el fondo de camarón",
    "3 hojas de laurel, para el fondo de camarón",
    "2 ramas de tomillo, para el fondo de camarón",
    "1/4 tazas de cilantro, para el fondo de camarón",
    "1 taza de zanahoria, en rebanadas, para el fondo de camarón",
    "1 taza de cebolla cambray, en rebanadas, para el fondo de camarón",
    "2 tazas de filete de mero, en cubos medianos",
    "2 tazas de camarón mediano, sin cáscara, limpios",
    "1 diente de ajo",
    "1 taza de jitomate, en cubos pequeños",
    "3 chiles de árbol",
    "3 epazotes",
    "al gusto de sal",
    "al gusto de pimienta",
    "al gusto de limón, para acompañar"
)
