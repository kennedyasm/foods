package com.example.foods

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class FoodRecipesTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, FakeApp::class.java.name, context)
    }

}
