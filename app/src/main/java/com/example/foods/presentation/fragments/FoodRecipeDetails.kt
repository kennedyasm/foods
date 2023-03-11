package com.example.foods.presentation.fragments

import android.os.Bundle
import com.example.foods.databinding.FragmentFoodRecipeDetailsBinding

class FoodRecipeDetails :
    BaseFragment<FragmentFoodRecipeDetailsBinding>(FragmentFoodRecipeDetailsBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}