package com.example.foods.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat

fun openAppSetting(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    ContextCompat.startActivity(context, intent, null)
}
