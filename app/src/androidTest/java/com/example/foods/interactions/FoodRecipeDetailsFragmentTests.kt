package com.example.foods.interactions

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.foods.R
import com.example.foods.core.containsTotalChildItemsInView
import com.example.foods.core.extensions.openItemInRecyclerViewByPosition
import com.example.foods.core.findStringInstanceByValueAndClickOn
import com.example.foods.core.findStringInstanceByValueAndSwipeUp
import com.example.foods.core.isDisplayedView
import com.example.foods.core.isDisplayedViewWithText
import com.example.foods.core.mediumScreenDelay
import com.example.foods.core.onClickInBackButton
import com.example.foods.core.onClickInDisplayedView
import com.example.foods.core.rootViewSwipeDown
import com.example.foods.core.rootViewSwipeUp
import com.example.foods.core.smallScreenDelay
import com.example.foods.presentation.holders.FoodRecipeViewHolder

fun userInteractionsInFoodRecipeDetailsFragment(device: UiDevice) {
    openTamalesDeMoleRecipeItemInFoodRecipeDetailsFragment()
    assertCorrectDisplayedViewsInFoodRecipeDetailsFragment()
    closeAppBarLayoutInFoodRecipeDetails()

    mediumScreenDelay()

    onClickIngredients()
    swipeInIngredientList()

    mediumScreenDelay()

    onClickStepsToPrepare()

    mediumScreenDelay()

    onClickIngredients()
    onClickStepsToPrepare()

    openAppBarLayoutInFoodRecipeDetails()

    mediumScreenDelay()

    openFoodRecipeOriginInMap()

    smallScreenDelay()

    assertCorrectMapMarkerTitle(device)

    mediumScreenDelay()

    onClickInBackButton()
    onClickInBackButton()
}

private fun openTamalesDeMoleRecipeItemInFoodRecipeDetailsFragment() {
    openItemInRecyclerViewByPosition<FoodRecipeViewHolder>(R.id.food_recipes_recycler_view, 5)
}

private fun assertCorrectDisplayedViewsInFoodRecipeDetailsFragment() {
    isDisplayedView(R.id.food_recipe_image_details)
    val descriptionText = "Los tamales de mole son todo un manjar, pues la combinación de" +
            " sabores es simplemente irresistible. Además, se preparan con hoja de plátano," +
            " la cual aporta mucho sabor y hace que queden muy húmedos y esponjosos. ¡Anímate" +
            " a preparar estos tamales de mole paso a paso!"
    isDisplayedViewWithText(R.id.food_recipe_description, descriptionText)
    isDisplayedViewWithText(R.id.food_recipe_origin, "Origen: Chiapas")
    isDisplayedViewWithText(R.id.see_map_location, "Ver en mapa")
    containsTotalChildItemsInView(R.id.preparation_ingredients_expandable_list, 2)
}

private fun closeAppBarLayoutInFoodRecipeDetails() {
    rootViewSwipeUp()
}

private fun openAppBarLayoutInFoodRecipeDetails() {
    rootViewSwipeDown()
}

private fun onClickIngredients() {
    findStringInstanceByValueAndClickOn("Ingredientes:")
}

private fun swipeInIngredientList() {
    findStringInstanceByValueAndSwipeUp("suficiente de hoja de plátano, para tamal, asadas")
}

private fun onClickStepsToPrepare() {
    findStringInstanceByValueAndClickOn("Pasos para preparar:")
}

private fun openFoodRecipeOriginInMap() {
    onClickInDisplayedView(R.id.see_map_location, "Ver en mapa")
}

private fun assertCorrectMapMarkerTitle(device: UiDevice) {
    val marker = device.findObject(UiSelector().descriptionContains("TAMALES DE MOLE"))
    marker.exists()
    marker.click()
}
