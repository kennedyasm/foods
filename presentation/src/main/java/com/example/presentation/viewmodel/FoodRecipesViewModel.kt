package com.example.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.usecases.GetFoodRecipesByQueryUseCase
import com.example.domain.usecases.GetFoodRecipesUseCase
import com.example.domain.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase,
    private val getFoodRecipesByQueryUseCase: GetFoodRecipesByQueryUseCase,
) : BaseViewModel() {

    private val _getFoodRecipesState: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val getFoodRecipesState: StateFlow<State>
        get() = _getFoodRecipesState

    init {

        getFoodRecipes()
    }

    fun getFoodRecipes() {
        loadingGetFoodRecipes()
        getFoodRecipesUseCase.invoke()
            .subscribe(::successGetFoodRecipes, ::errorGetFoodRecipes)
            .also(disposables::add)
    }

    fun getFoodRecipesByQuery(query: String) {
        loadingGetFoodRecipes()
        viewModelScope.launch {
            getFoodRecipesByQueryUseCase(query).distinctUntilChanged().collectLatest {
                _getFoodRecipesState.value = State.Success(it)
            }
        }
    }

    private fun successGetFoodRecipes(foodRecipes: List<FoodRecipeItemUi>) {
        _getFoodRecipesState.value = State.Success(foodRecipes)
    }

    private fun errorGetFoodRecipes(throwable: Throwable) {
        _getFoodRecipesState.value = State.Error(throwable)
    }

    private fun loadingGetFoodRecipes() {
        _getFoodRecipesState.value = State.Loading
    }
}
