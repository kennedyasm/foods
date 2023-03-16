package com.example.foods.interactions

import com.example.foods.R
import com.example.foods.core.clearTextInDisplayedView
import com.example.foods.core.containsTotalChildItemsInView
import com.example.foods.core.isDisplayedViewWithHintText
import com.example.foods.core.isDisplayedViewWithText
import com.example.foods.core.isEnabledView
import com.example.foods.core.isHiddenView
import com.example.foods.core.largeScreenDelay
import com.example.foods.core.typeTextInDisplayedView


fun userInteractionInFoodRecipesFragment() {
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

private fun assertCorrectDisplayedItemViewsWhenTextIsTypedInSearchView() {
    typeTextInDisplayedView(androidx.appcompat.R.id.search_src_text, "bowl")

    largeScreenDelay()

    isDisplayedViewWithText(R.id.total_results_text, "Resultados \n1")
    containsTotalChildItemsInView(R.id.food_recipes_recycler_view, 1)
    clearTextInDisplayedView(androidx.appcompat.R.id.search_src_text)

    largeScreenDelay()
}
