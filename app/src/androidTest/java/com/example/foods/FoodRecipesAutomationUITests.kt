package com.example.foods

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.foods.common.Mocks
import com.example.foods.common.enqueueOkHttpJsonResponse
import com.example.foods.presentation.MainActivity
import com.example.foods.presentation.fragments.FoodRecipesFragment.Companion.DEBOUNCE_TIME
import com.example.foods.presentation.holders.FoodRecipeViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
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
        get() = Mocks.mockWebServer

    @Before
    fun setUp() {
        mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")
        mockWebServer.start()
    }

    @Test
    fun testFirstScreen() {
        assertCorrectDisplayedViewsInFoodRecipesFragment()
        assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView()
        openTamalesDeMoleRecipeItem()

    }

    private fun assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView() {
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(ViewActions.typeText("bowl"))

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))

        onView(allOf(withId(R.id.total_results_text), withText("Resultados \n1")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.food_recipes_recycler_view))
            .check(matches(hasChildCount(1)))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(ViewActions.clearText())

        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))
    }

    private fun assertCorrectDisplayedViewsInFoodRecipesFragment() {
        onView(withId(R.id.linear_progress_indicator)).check(matches(not( isDisplayed())))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .check(matches(isEnabled()))

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .check(matches(withHint("buscar")))

        onView(allOf(withId(R.id.total_results_text), withText("Resultados \n6")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.food_recipes_recycler_view))
            .check(matches(hasChildCount(6)))
    }

    private fun openTamalesDeMoleRecipeItem() {
        openItemInRecyclerViewByPosition<FoodRecipeViewHolder>(R.id.food_recipes_recycler_view, 5)
        onView(withId(R.id.food_recipe_image_details)).check(matches(isDisplayed()))
        closeAppBarLayoutInFoodRecipeDetails()

    }

    private fun closeAppBarLayoutInFoodRecipeDetails() {
        onView(isRoot()).perform(swipeUp())
        onView(isRoot()).perform(waitFor(DEBOUNCE_TIME + 100L))
    }

    private fun <T: ViewHolder> openItemInRecyclerViewByPosition(@IdRes id: Int, position: Int) {
        val recyclerViewInteraction = onView(withId(id))
        recyclerViewInteraction.run {
            scrollToInRecyclerView<T>(position)
            clickInRecyclerPositionItem<T>(position)
        }
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

    companion object {
        fun <T : ViewHolder> ViewInteraction.scrollToInRecyclerView(position: Int) {
            perform(RecyclerViewActions.scrollToPosition<T>(position))
        }

        fun <T : ViewHolder> ViewInteraction.clickInRecyclerPositionItem(position: Int) {
            perform(RecyclerViewActions.actionOnItemAtPosition<T>(position, click()))
        }
    }


}