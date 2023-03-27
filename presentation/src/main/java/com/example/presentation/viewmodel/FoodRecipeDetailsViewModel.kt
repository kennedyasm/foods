package com.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
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
) : BaseViewModel() {

    val foodRecipeDetails: StateFlow<State> = flow<State> {
        val data = getFoodRecipeDetailsByIdUseCase(
            savedStateHandle.get<String>("food_recipe_id")?.toIntOrNull() ?: 0
        )
        emit(State.Success(data))

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = State.Loading,
    )

}
