package com.example.foods.features

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.foods.FoodRecipesUiTest.Companion.UNTIL_TIME_TO_EXIST

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.locationScreenTest(device: UiDevice) {
    this.waitUntilAtLeastOneExists(hasTestTag("food recipe location map"), UNTIL_TIME_TO_EXIST)

    val titleMarker = device.findObject(UiSelector().descriptionContains("TAMALES DE MOLE"))
    titleMarker.exists()
    titleMarker.click()
}