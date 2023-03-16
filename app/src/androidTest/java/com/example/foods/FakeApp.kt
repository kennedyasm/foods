package com.example.foods

import android.os.Build
import android.os.StrictMode
import com.example.foods.di.DaggerFakeApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FakeApp : App() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > 8)
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    override fun initializeComponent(): AndroidInjector<out DaggerApplication> {
        return DaggerFakeApplicationComponent.factory().create(applicationContext)
    }
}
