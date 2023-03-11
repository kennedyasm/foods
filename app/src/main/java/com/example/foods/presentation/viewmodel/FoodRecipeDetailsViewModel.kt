package com.example.foods.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foods.domain.usecases.GetFoodRecipeByIdUseCase
import javax.inject.Inject

class FoodRecipeDetailsViewModel @Inject constructor(
    private val getFoodRecipeByIdUseCase: GetFoodRecipeByIdUseCase
) : ViewModel() {



}