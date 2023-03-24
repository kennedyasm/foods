package com.example.common.presentation.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

fun <T> ViewGroup.binding(
    bind: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> T,
    attachToParent: Boolean = false,
): T = bind.invoke(LayoutInflater.from(this.context), this, attachToParent)
