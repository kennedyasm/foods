package com.example.foods

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.foods.common.enqueueOkHttpJsonResponse
import com.example.foods.core.*
import com.example.foods.core.extensions.openItemInRecyclerViewByPosition
import com.example.foods.presentation.MainActivity
import com.example.foods.presentation.holders.FoodRecipeViewHolder
import org.hamcrest.CoreMatchers.*
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
        userInteractionOnFoodRecipesFragment()
        userInteractionsOnFoodRecipeDetailsFragment()
    }

    private fun userInteractionOnFoodRecipesFragment() {
        assertCorrectDisplayedViewsInFoodRecipesFragment()
        assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView()
    }

    private fun assertCorrectDisplayedViewsInFoodRecipesFragment() {
        isHiddenView(R.id.linear_progress_indicator)
        isEnabledView(androidx.appcompat.R.id.search_src_text)
        isDisplayedViewWithHintText(androidx.appcompat.R.id.search_src_text, "buscar")
        isDisplayedViewWithText(R.id.total_results_text, "Resultados \n6")
        containsTotalChildItemsInView(R.id.food_recipes_recycler_view, 6)
    }

    private fun userInteractionsOnFoodRecipeDetailsFragment() {
        openTamalesDeMoleRecipeItemInFoodRecipeDetailsFragment()
        assertCorrectDisplayedViewsInFoodRecipeDetailsFragment()
        closeAppBarLayoutInFoodRecipeDetails()

        mediumScreenDelay()

        clickOnIngredients()
        swipeInIngredientList()

        mediumScreenDelay()

        clickOnStepsToPrepare()

        mediumScreenDelay()

        clickOnIngredients()
        clickOnStepsToPrepare()

        openAppBarLayoutInFoodRecipeDetails()

        mediumScreenDelay()

        openFoodRecipeOriginInMap()

        mediumScreenDelay()

        onClickInBackButton()
        onClickInBackButton()
    }

    private fun clickOnIngredients() {
        findStringInstanceByValueAndClickOn("Ingredientes:")
    }

    private fun swipeInIngredientList() {
        findStringInstanceByValueAndSwipeUp("suficiente de hoja de plátano, para tamal, asadas")
    }

    private fun clickOnStepsToPrepare() {
        findStringInstanceByValueAndClickOn("Pasos para preparar:")
    }

    private fun assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView() {
        typeTextInDisplayedView(androidx.appcompat.R.id.search_src_text, "bowl")

        largeScreenDelay()

        isDisplayedViewWithText(R.id.total_results_text, "Resultados \n1")
        containsTotalChildItemsInView(R.id.food_recipes_recycler_view, 1)
        clearTextInDisplayedView(androidx.appcompat.R.id.search_src_text)

        largeScreenDelay()
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

    private fun openFoodRecipeOriginInMap() {
        onClickInDisplayedView(R.id.see_map_location, "Ver en mapa")
    }

    private fun closeAppBarLayoutInFoodRecipeDetails() {
        rootViewSwipeUp()
    }

    private fun openAppBarLayoutInFoodRecipeDetails() {
        rootViewSwipeDown()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
