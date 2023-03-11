package com.example.foods.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> Fragment.getViewModel(): T {
    return ViewModelProvider(requireActivity())[T::class.java]
}

fun Fragment.getIntOrDefault(argumentName: String): Int {
    return arguments?.getInt(argumentName, 0) ?: 0
}
