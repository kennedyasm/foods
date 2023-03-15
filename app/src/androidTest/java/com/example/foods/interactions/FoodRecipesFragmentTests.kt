package com.example.foods

import com.example.foods.core.*

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
