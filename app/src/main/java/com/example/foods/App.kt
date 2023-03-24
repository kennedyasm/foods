package com.example.foods

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {
/*
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return initializeComponent()
    }
    */
/*
    open fun initializeComponent(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
            .also { it.inject(this) }
    }
    */
}
