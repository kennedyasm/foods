package com.example.foods.core.testing.doubles

import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.models.FoodRecipeLocationMapUi

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

fun provideFoodRecipeDetailsUi() =
    FoodRecipeDetailsUi(
        2,
        "CALDO DE PIEDRA",
        "https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg",
        "Prueba esta receta de caldo de piedra, ya que se trata de un platillo elaborado por la comunidad de San Felipe Usila, en Oaxaca. Por si fuera poco, este caldo data de la época prehispánica y se prepara en una jícara, en donde se mezcla el pescado, el camarón, las verduras, el caldo y, por último, una piedra al rojo vivo. En esta ocasión, en kiwilimón prepararemos un caldo de piedra en molcajete, ¡atrévete a probarlo!",
        "Oaxaca",
        caldoDePiedraIngredients,
        caldoDePiedraPreparation
    )

fun provideFoodRecipeLocationMapUi() =
    FoodRecipeLocationMapUi(
        2,
        "CALDO DE PIEDRA",
        16.762218717945395,
        -96.9274728874135
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

val caldoDePiedraPreparation = listOf(
    "1.- Calienta el aceite vegetal en una olla a fuego medio, agrega las cabezas de camarón, la zanahoria y la cebolla. Cocina por 5 minutos o hasta que cambien de color. Posteriormente, vierte el agua, el epazote, el laurel, el tomillo y el cilantro. Cocina a fuego medio por 30 minutos. Retira del fuego, cuela, reserva y mantén el caldo caliente.",
    "2.- Calienta el molcajete en una estufa u horno, pero recuerda que tiene que estar muy caliente para que pueda cocerse. Cuando esté bien caliente, retira con guantes y coloca sobre una tabla de madera. Agrega inmediatamente el pescado y el camarón. Mezcla con una pala de madera y cocina por 5 minutos. Vierte el caldo de camarón hasta cubrir y añade el ajo, el jitomate, el chile de árbol, el epazote y sazona con sal, pimienta. Cocina el caldo de piedra por 5 minutos más.",
    "3.- Sirve el caldo de piedra en el mismo molcajete y acompaña con limón."
)
