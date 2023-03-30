package com.example.data.source.network.asserts

import com.example.foods.data.source.network.dto.FoodRecipesResponseDto
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

fun assertGetFoodRecipesResponseDto(dtoResponse: FoodRecipesResponseDto) = dtoResponse.run {
    assertThat(foodRecipes.size, equalTo(2))
    foodRecipes[1].run {
        assertThat(id, equalTo(2))
        assertThat(name, equalTo("CALDO DE PIEDRA"))
        assertThat(
            description,
            equalTo("Prueba esta receta de caldo de piedra, ya que se trata de un platillo elaborado por la comunidad de San Felipe Usila, en Oaxaca. Por si fuera poco, este caldo data de la época prehispánica y se prepara en una jícara, en donde se mezcla el pescado, el camarón, las verduras, el caldo y, por último, una piedra al rojo vivo. En esta ocasión, en kiwilimón prepararemos un caldo de piedra en molcajete, ¡atrévete a probarlo!")
        )
        assertThat(ingredients.size, equalTo(18))
        assertThat(preparation.size, equalTo(3))
        assertThat(
            imageUrl,
            equalTo("https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg")
        )
        assertThat(origin, equalTo("Oaxaca"))
        assertThat(latitude, equalTo(16.762218717945395))
        assertThat(longitude, equalTo(-96.9274728874135))
    }
}
