package com.example.foods.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foods.core.State
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import javax.inject.Inject

class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase
) : BaseViewModel() {

    private val _getFoodRecipesState: MutableLiveData<State> = MutableLiveData()
    val getFoodRecipesState: LiveData<State>
        get() = _getFoodRecipesState

    fun getFoodRecipes() {
        getFoodRecipesUseCase.getFoodRecipes()
            .subscribe(::successGetFoodRecipes, ::errorGetFoodRecipes)
            .also(disposables::add)
    }

    private fun successGetFoodRecipes(foodRecipes: List<FoodRecipeItemDTO>) {
        _getFoodRecipesState.value = State.Success(foodRecipes)
    }

    private fun errorGetFoodRecipes(throwable: Throwable) {
        _getFoodRecipesState.value = State.Error(throwable)
    }
}
