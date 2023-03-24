package com.example.common.presentation.extensions

import androidx.fragment.app.Fragment

fun Fragment.getIntOrDefault(argumentName: String): Int {
    return arguments?.getInt(argumentName, 0) ?: 0
}

fun Fragment.getStringOrDefault(argumentName: String): String {
    return arguments?.getString(argumentName, "") ?: ""
}
