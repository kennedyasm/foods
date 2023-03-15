package com.example.foods

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.foods.core.enqueueOkHttpJsonResponse
import com.example.foods.core.*
import com.example.foods.interactions.userInteractionInFoodRecipesFragment
import com.example.foods.interactions.userInteractionsInFoodRecipeDetailsFragment
import com.example.foods.presentation.MainActivity
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FoodRecipesAppCompleteUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val mockWebServer get() = MySharedMockWebServer.mockWebServer
    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")
        mockWebServer.start()
    }

    @Test
    fun mainActivityTest() {
        userInteractionInFoodRecipesFragment()
        userInteractionsInFoodRecipeDetailsFragment(device)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
