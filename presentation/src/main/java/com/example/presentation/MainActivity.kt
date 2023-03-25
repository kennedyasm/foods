package com.example.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.example.common.design.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            AppTheme() {
                FoodRecipesApp()
            }
        }
    }
}
