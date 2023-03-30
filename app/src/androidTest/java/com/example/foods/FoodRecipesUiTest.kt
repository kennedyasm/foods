package com.example.foods

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.foods.core.testing.MySharedMockWebServer.mockWebServer
import com.example.foods.core.testing.enqueueForbiddenResponse
import com.example.foods.core.testing.enqueueOkHttpJsonResponse
import com.example.foods.features.detailsScreenTest
import com.example.foods.features.homeScreenTest
import com.example.foods.features.locationScreenTest
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@HiltAndroidTest
class FoodRecipesUiTest {

    private lateinit var device: UiDevice

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    @get:Rule(order = 1)
    val tempFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mockWebServer.enqueueForbiddenResponse()
    }

    @Test
    fun mainActivity() = composeTestRule.run {
        homeScreenTest(onOpenDetails = {
            detailsScreenTest(onOpenLocation = { locationScreenTest(device) })
        }, onErrorRetry = {
            mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")
        })

        backStackTest(device)
    }

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeTestRule.backStackTest(device: UiDevice) = this.run {
        device.pressBack()
        waitUntilAtLeastOneExists(hasTestTag("food description container"), UNTIL_TIME_TO_EXIST)
        device.pressBack()
        waitUntilAtLeastOneExists(hasTestTag("food recipes search"), UNTIL_TIME_TO_EXIST)
    }

    companion object {
        const val UNTIL_TIME_TO_EXIST: Long = 3_000
    }
}
