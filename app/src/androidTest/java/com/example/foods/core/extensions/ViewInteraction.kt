package com.example.foods.core.extensions

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers

fun <T : RecyclerView.ViewHolder> ViewInteraction.scrollToInRecyclerView(position: Int): ViewInteraction {
    return perform(RecyclerViewActions.scrollToPosition<T>(position))
}

fun <T : RecyclerView.ViewHolder> ViewInteraction.clickOnItemPositionInRecyclerView(position: Int): ViewInteraction {
    return perform(RecyclerViewActions.actionOnItemAtPosition<T>(position, ViewActions.click()))
}

fun <T : RecyclerView.ViewHolder> openItemInRecyclerViewByPosition(@IdRes id: Int, position: Int) {
    val recyclerViewInteraction = Espresso.onView(ViewMatchers.withId(id))
    recyclerViewInteraction.run {
        scrollToInRecyclerView<T>(position)
        clickOnItemPositionInRecyclerView<T>(position)
    }
}
