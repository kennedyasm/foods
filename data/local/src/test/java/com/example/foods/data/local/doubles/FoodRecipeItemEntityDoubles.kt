package com.example.foods.data.local.doubles

import com.example.foods.data.local.database.entities.FoodRecipeItemEntity

fun provideFoodRecipeItemEntityList() = listOf(
    provideBowThaiFoodRecipeItemEntity(),
    provideCaldoDePiedraFoodRecipeItemEntity()
)

fun provideBowThaiFoodRecipeItemEntity() =
    FoodRecipeItemEntity(
        1,
        "BOWL THAI",
        "¿Eres fan de la comida tailandesa? Entonces prepara este bowl thai elaborado con una mezcla de arroz sazonado con salsa de soya, aceite de ajonjolí y coronado con pechuga a la parrilla, la cual tiene un delicioso toque agridulce, ¡es la combinación perfecta entre sabor y frescura! ¡Dales sazón a tus alimentos con la Conserva McCormick Balance fresa con chía, elaborada con ingredientes 100% naturales!",
        bowlThaiIngredients,
        bowlThaiPreparation,
        "https://i0.wp.com/bestofvegan.com/wp-content/uploads/2020/12/Thai-Noodle-Bowl.jpg",
        "Tahilandia",
        15.322381634068169,
        101.32069488271185
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

val bowlThaiPreparation = listOf(
    "1.- Para marinar: Mezcla la pechuga de pollo, la Conserva de fresa con chía McCormick® Balance, la salsa de soya, el jengibre, el ajo y las rebanadas de piña en un bowl. Marina en refrigeración.",
    "2.- En una parrilla a fuego medio, cocina la pechuga de pollo y las rebanadas de piña.",
    "3.- Agrega el arroz en un bowl y mezcla con aceite de ajonjolí, ajonjolí negro, la salsa de soya y los tallos de cebolla cambray. Mezcla hasta integrar.",
    "4.- Sirve el arroz en un bowl y agrega el germen de soya, la col morada, los edamames, el kale frito, la pechuga de pollo en rebanadas y la piña.",
    "5.- Decora el bowl thai con cilantro y acompaña con limón y hojuelas de chile."
)

fun provideCaldoDePiedraFoodRecipeItemEntity() =
    FoodRecipeItemEntity(
        2,
        "CALDO DE PIEDRA",
        "Prueba esta receta de caldo de piedra, ya que se trata de un platillo elaborado por la comunidad de San Felipe Usila, en Oaxaca. Por si fuera poco, este caldo data de la época prehispánica y se prepara en una jícara, en donde se mezcla el pescado, el camarón, las verduras, el caldo y, por último, una piedra al rojo vivo. En esta ocasión, en kiwilimón prepararemos un caldo de piedra en molcajete, ¡atrévete a probarlo!",
        caldoDePiedraIngredients,
        caldoDePiedraPreparation,
        "https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg",
        "Oaxaca",
        16.762218717945395,
        -96.9274728874135
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

