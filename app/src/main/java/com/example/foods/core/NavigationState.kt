package com.example.foods.core

data class NavigationState(val id: Int, val data: Any? = null) {

    @Suppress("UNCHECKED_CAST")
    fun <T> toData(): T = data as T
}
