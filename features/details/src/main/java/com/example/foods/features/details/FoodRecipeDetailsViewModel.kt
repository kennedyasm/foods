package com.example.foods.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.State
import com.example.domain.usecases.GetFoodRecipeDetailsByIdUseCase
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
        savedStateHandle.get<String>("food_recipe_id")?.toIntOrNull()?.let {
            emit(State.Success(getFoodRecipeDetailsByIdUseCase(it)))
        }?:emit(State.Error(Throwable()))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = State.Loading,
    )

}
