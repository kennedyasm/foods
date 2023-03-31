package com.example.foods.features

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import com.example.foods.FoodRecipesUiTest.Companion.UNTIL_TIME_TO_EXIST

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.detailsScreenTest(onOpenLocation: () -> Unit) = this.run {

    waitUntilAtLeastOneExists(hasTestTag("food description container"), UNTIL_TIME_TO_EXIST)
    onNodeWithTag("food description container").performTouchInput {
        swipeUp()
    }

    waitUntilAtLeastOneExists(hasTestTag("steps to cook"), UNTIL_TIME_TO_EXIST)
    onNodeWithTag("food description container").performTouchInput {
        swipeDown()
    }

    onNodeWithText("ver en mapa", ignoreCase = true).performClick()

    onOpenLocation.invoke()
}
