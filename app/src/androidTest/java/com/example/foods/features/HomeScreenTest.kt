package com.example.foods.features

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.example.foods.FoodRecipesUiTest.Companion.UNTIL_TIME_TO_EXIST

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.homeScreenTest(onOpenDetails: () -> Unit, onErrorRetry: () -> Unit) = this.run {

    onNodeWithTag("food recipes loader").assertIsDisplayed()

    waitUntilAtLeastOneExists(hasTestTag("error retry button"), UNTIL_TIME_TO_EXIST)
    onNodeWithTag("error retry button").performClick()

    waitUntilAtLeastOneExists(hasTestTag("food recipes loader"), UNTIL_TIME_TO_EXIST)
    onNodeWithTag("food recipes loader").assertIsDisplayed()

    onErrorRetry.invoke()

    waitUntilAtLeastOneExists(hasTestTag("food recipes search"), UNTIL_TIME_TO_EXIST)

    onNodeWithText("Buscar receta").performTextInput("tamales")

    waitUntilAtLeastOneExists(hasTestTag("search back button"), UNTIL_TIME_TO_EXIST)
    waitUntilAtLeastOneExists(hasTestTag("clean search text button"), UNTIL_TIME_TO_EXIST)

    onNodeWithTag("search back button").performClick()

    onRoot(useUnmergedTree = true).printToLog("TAG")

    waitUntilAtLeastOneExists(hasText("tamales de mole", ignoreCase = true), UNTIL_TIME_TO_EXIST)

    onNodeWithText("tamales de mole", ignoreCase = true)
        .assertHasClickAction()
        .performClick()

    onOpenDetails.invoke()
}
