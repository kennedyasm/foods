package com.example.foods.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foods.core.State
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import javax.inject.Inject

class FoodRecipeDetailsViewModel @Inject constructor(
    private val getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase
) : BaseViewModel() {

    private val _getFoodRecipeDetails: MutableLiveData<State> = MutableLiveData()
    val getFoodRecipeDetails: LiveData<State> get() = _getFoodRecipeDetails

    fun getFoodRecipeDetailsById(id: Int) {
        getFoodRecipeDetailsByIdUseCase(id)
            .subscribe(::getFoodRecipeDetailsSuccess, ::getFoodRecipeDetailsError)
            .also(disposables::add)
    }

    private fun getFoodRecipeDetailsSuccess(foodRecipeDetailsUi: FoodRecipeDetailsUi) {
        _getFoodRecipeDetails.value = State.Success(foodRecipeDetailsUi)
    }

    private fun getFoodRecipeDetailsError(throwable: Throwable) {
        _getFoodRecipeDetails.value = State.Error(throwable)
    }
}