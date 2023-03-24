package com.example.common.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imagePath: String) {
    Glide.with(this)
        .load(imagePath)
        .placeholder(android.R.drawable.stat_sys_download)
        .into(this)
}
