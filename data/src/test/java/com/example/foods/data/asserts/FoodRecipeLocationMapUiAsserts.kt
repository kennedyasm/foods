package com.example.foods.data.asserts

import com.example.foods.domain.models.FoodRecipeLocationMapUi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

fun assertFoodRecipeLocationMapUi(item: FoodRecipeLocationMapUi) = item.run {
    assertThat(id, equalTo(2))
    assertThat(name, equalTo("CALDO DE PIEDRA"))
    assertThat(latitude, equalTo(16.762218717945395))
    assertThat(longitude, equalTo(-96.9274728874135))
}
