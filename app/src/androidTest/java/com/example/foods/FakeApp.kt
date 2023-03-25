package com.example.foods

import android.os.Build
import android.os.StrictMode

class FakeApp : App() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > 8)
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

}
