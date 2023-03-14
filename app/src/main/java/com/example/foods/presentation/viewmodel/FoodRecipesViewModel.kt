package com.example.foods.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.foods.core.State
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.usecases.GetFoodRecipesByQueryUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase,
    private val getFoodRecipesByQueryUseCase: GetFoodRecipesByQueryUseCase
) : BaseViewModel() {

    private val _getFoodRecipesState: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val getFoodRecipesState: StateFlow<State>
        get() = _getFoodRecipesState

    init {
        getFoodRecipes()
    }

    fun getFoodRecipes() {
        getFoodRecipesUseCase.invoke()
            .subscribe(::successGetFoodRecipes, ::errorGetFoodRecipes)
            .also(disposables::add)
    }

    private fun successGetFoodRecipes(foodRecipes: List<FoodRecipeItemUi>) {
        Log.d("kTest -> ", " successGetFoodRecipes")
        _getFoodRecipesState.value = State.Success(foodRecipes)
    }

    private fun errorGetFoodRecipes(throwable: Throwable) {
        _getFoodRecipesState.value = State.Error(throwable)
    }

    fun getFoodRecipesByQuery(query: String) {
        viewModelScope.launch {
            getFoodRecipesByQueryUseCase(query).distinctUntilChanged().collectLatest {
                _getFoodRecipesState.value = State.Success(it)
            }
        }
    }
}
