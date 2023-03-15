package com.example.foods

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.foods.core.MySharedMockWebServer
import com.example.foods.common.enqueueOkHttpJsonResponse
import com.example.foods.core.extensions.clickOnItemPositionInRecyclerView
import com.example.foods.core.extensions.openItemInRecyclerViewByPosition
import com.example.foods.core.extensions.scrollToInRecyclerView
import com.example.foods.presentation.MainActivity
import com.example.foods.presentation.fragments.FoodRecipesFragment.Companion.DEBOUNCE_TIME
import com.example.foods.presentation.holders.FoodRecipeViewHolder
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class FoodRecipesAutomationUITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val mockWebServer
        get() = MySharedMockWebServer.mockWebServer

    @Before
    fun setUp() {
        mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")
        mockWebServer.start()
    }

    @Test
    fun mainActivityTest() {
        assertUserInteractionOnFoodRecipesFragment()
        assertUserInteractionsOnFoodRecipeDetailsFragment()
    }

    private fun assertUserInteractionOnFoodRecipesFragment() {
        assertCorrectDisplayedViewsInFoodRecipesFragment()
        assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView()
    }

    private fun assertUserInteractionsOnFoodRecipeDetailsFragment() {
        openTamalesDeMoleRecipeItemInFoodRecipeDetailsFragment()
        assertCorrectDisplayedViewsInFoodRecipeDetailsFragment()

        closeAppBarLayoutInFoodRecipeDetails()

        clickOnIngredients()

        swipeInIngredientList()

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        clickOnStepsToPrepare()

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        clickOnIngredients()
        clickOnStepsToPrepare()

        openAppBarLayoutInFoodRecipeDetails()
        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))
        openFoodRecipeOriginInMap()

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        onView(isRoot()).perform(pressBack())
        onView(isRoot()).perform(pressBack())
    }

    private fun clickOnIngredients() {
        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("Ingredientes:")
            )
        ).perform(click())

    }

    private fun swipeInIngredientList() {
        onData(allOf(`is`(instanceOf(String::class.java)),
            `is`("suficiente de hoja de plátano, para tamal, asadas"))).perform(swipeUp())

    }

    private fun clickOnStepsToPrepare() {
        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("Pasos para preparar:")
            )
        ).perform(click())
    }

    private fun assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView() {
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(ViewActions.typeText("bowl"))

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        onView(withId(R.id.total_results_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("Resultados \n1")))

        onView(withId(R.id.food_recipes_recycler_view))
            .check(matches(hasChildCount(1)))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(ViewActions.clearText())

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))
    }

    private fun assertCorrectDisplayedViewsInFoodRecipesFragment() {
        onView(withId(R.id.linear_progress_indicator)).check(matches(not(isDisplayed())))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .check(matches(isEnabled()))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .check(matches(withHint("buscar")))

        onView(withId(R.id.total_results_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("Resultados \n6")))

        onView(withId(R.id.food_recipes_recycler_view))
            .check(matches(hasChildCount(6)))
    }

    private fun openTamalesDeMoleRecipeItemInFoodRecipeDetailsFragment() {
        openItemInRecyclerViewByPosition<FoodRecipeViewHolder>(R.id.food_recipes_recycler_view, 5)
    }

    private fun assertCorrectDisplayedViewsInFoodRecipeDetailsFragment() {
        onView(withId(R.id.food_recipe_image_details)).check(matches(isDisplayed()))

        val descriptionText =
            "Los tamales de mole son todo un manjar, pues la combinación de sabores es simplemente irresistible. Además, se preparan con hoja de plátano, la cual aporta mucho sabor y hace que queden muy húmedos y esponjosos. ¡Anímate a preparar estos tamales de mole paso a paso!"

        onView(withId(R.id.food_recipe_description))
            .check(matches(isDisplayed()))
            .check(matches(withText(descriptionText)))

        onView(withId(R.id.food_recipe_origin))
            .check(matches(isDisplayed()))
            .check(matches(withText("Origen: Chiapas")))

        onView(withId(R.id.see_map_location))
            .check(matches(isDisplayed()))
            .check(matches(withText("Ver en mapa")))

        onView(withId(R.id.preparation_ingredients_expandable_list))
            .check(matches(hasChildCount(2)))
    }

    private fun openFoodRecipeOriginInMap() {
        onView(allOf(withId(R.id.see_map_location), withText("Ver en mapa")))
            .perform(click())
    }

    private fun closeAppBarLayoutInFoodRecipeDetails() {
        onView(isRoot()).perform(swipeUp())
        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))
    }

    private fun openAppBarLayoutInFoodRecipeDetails() {
        onView(isRoot()).perform(swipeDown())
    }


    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
