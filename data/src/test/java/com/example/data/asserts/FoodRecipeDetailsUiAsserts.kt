package com.example.data.asserts

import com.example.foods.domain.models.FoodRecipeDetailsUi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

fun assertFoodRecipeDetailsUi(item: FoodRecipeDetailsUi) = item.run {
    assertThat(imageUrl, equalTo("https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg"))
    assertThat(description, equalTo("Prueba esta receta de caldo de piedra, ya que se trata de un platillo elaborado por la comunidad de San Felipe Usila, en Oaxaca. Por si fuera poco, este caldo data de la época prehispánica y se prepara en una jícara, en donde se mezcla el pescado, el camarón, las verduras, el caldo y, por último, una piedra al rojo vivo. En esta ocasión, en kiwilimón prepararemos un caldo de piedra en molcajete, ¡atrévete a probarlo!"))
    assertThat(origin, equalTo("Oaxaca"))
    assertThat(ingredients.size, equalTo(18))
    assertThat(preparation.size, equalTo(3))
}
