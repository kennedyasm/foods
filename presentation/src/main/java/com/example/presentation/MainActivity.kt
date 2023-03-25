package com.example.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        /*
        setContent {
            AppTheme() {
                FoodRecipesApp()
            }
        }*/
    }
}
