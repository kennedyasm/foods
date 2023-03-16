package com.example.foods.core

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

private const val SMALL_SCREEN_DELAY = 300L
private const val MEDIUM_SCREEN_DELAY = 600L
private const val LARGE_SCREEN_DELAY = 900L

fun isHiddenView(@IdRes id: Int) {
    onView(withId(id)).check(matches(not(isDisplayed())))
}

fun isEnabledView(@IdRes id: Int) {
    onView(withId(id)).check(matches(isEnabled()))
}

fun isDisabledView(@IdRes id: Int) {
    onView(withId(id)).check(matches(not(isEnabled())))
}

fun isDisplayedView(@IdRes id: Int) {
    onView(withId(id)).check(matches(isDisplayed()))
}

fun isDisplayedViewWithHintText(@IdRes id: Int, text: String) {
    onView(withId(id)).check(matches(withHint(text)))
}

fun isDisplayedViewWithText(@IdRes id: Int, text: String) {
    onView(withId(id)).check(matches(isDisplayed())).check(matches(withText(text)))
}

fun containsTotalChildItemsInView(@IdRes id: Int, childCount: Int) {
    onView(withId(id)).check(matches(hasChildCount(childCount)))
}

fun typeTextInDisplayedView(@IdRes id: Int, text: String)  {
    onView(withId(id)).check(matches(isDisplayed())).perform(typeText(text))
}

fun clearTextInDisplayedView(@IdRes id: Int) {
    onView(withId(id)).check(matches(isDisplayed())).perform(clearText())
}

fun rootViewSwipeUp() {
    onView(isRoot()).perform(swipeUp())
}

fun rootViewSwipeDown() {
    onView(isRoot()).perform(swipeDown())
}

fun findStringInstanceByValueAndClickOn(value: String) {
    onData(allOf(`is`(instanceOf(String::class.java)), `is`(value))).perform(click())
}

fun findStringInstanceByValueAndSwipeUp(value: String) {
    onData(allOf(`is`(instanceOf(String::class.java)), `is`(value))).perform(click())
}

fun onClickInDisplayedView(@IdRes id: Int, text: String) {
    onView(withId(id))
        .check(matches(withText(text)))
        .check(matches(isDisplayed()))
        .perform(click())
}

fun onClickInBackButton() {
    onView(isRoot()).perform(pressBack())
}

fun smallScreenDelay() {
    delayScreen(SMALL_SCREEN_DELAY)
}

fun mediumScreenDelay() {
    delayScreen(MEDIUM_SCREEN_DELAY)
}

fun largeScreenDelay() {
    delayScreen(LARGE_SCREEN_DELAY)
}

private fun delayScreen(delay: Long) {
    onView(isRoot()).perform(waitFor(delay))
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
