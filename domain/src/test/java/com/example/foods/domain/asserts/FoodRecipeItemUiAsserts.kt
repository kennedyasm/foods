package com.example.foods.domain.asserts

import com.example.foods.domain.models.FoodRecipeItemUi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

fun assertFoodRecipeItemUiList(items: List<FoodRecipeItemUi>) = items.run {
    assertThat(items.size, equalTo(2))
    assertFoodRecipeItemUi(this[1])
}

fun assertFoodRecipeItemUi(item: FoodRecipeItemUi) = item.run {
    assertThat(id, equalTo(2))
    assertThat(name, equalTo("CALDO DE PIEDRA"))
    assertThat(imageUrl, equalTo("https://www.cocinavital.mx/wp-content/uploads/2017/09/caldo-de-piedra.jpg"))
}
