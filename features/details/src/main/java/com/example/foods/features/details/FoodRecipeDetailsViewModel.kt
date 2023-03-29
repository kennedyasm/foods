package com.example.foods.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foods.domain.State
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoodRecipeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase
) : ViewModel() {

    val foodRecipeDetails: StateFlow<State> = flow {
        savedStateHandle.get<String>(RECIPE_ID)?.toIntOrNull()?.let {
            emit(State.Success(getFoodRecipeDetailsByIdUseCase(it)))
        } ?: emit(State.Error(Throwable()))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = State.Loading,
    )

    companion object {
        private const val RECIPE_ID = "food_recipe_id"
    }
}
