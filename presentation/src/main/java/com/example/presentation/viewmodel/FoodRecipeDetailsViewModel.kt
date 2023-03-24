package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.FoodRecipeDetailsUi
import com.example.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import com.example.domain.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodRecipeDetailsViewModel @Inject constructor(
    private val getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase
) : BaseViewModel() {

    private val _getFoodRecipeDetails: MutableLiveData<State> = MutableLiveData()
    val getFoodRecipeDetails: LiveData<State> get() = _getFoodRecipeDetails

    fun getFoodRecipeDetailsById(id: Int) {
        _getFoodRecipeDetails.value = State.Loading
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
