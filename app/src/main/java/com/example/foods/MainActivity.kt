package com.example.foods

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import com.example.foods.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // val points = EntryPointAccessors.fromApplication(this,Data::class.java)
        //points.foodRecipesRepository()

    }
}
