package com.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.State
import com.example.domain.models.FoodRecipeMapDetailUi
import com.example.domain.usecases.GetFoodRecipeMapDetailByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoodRecipeDetailMapViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodRecipeMapDetailByIdUseCase: GetFoodRecipeMapDetailByIdUseCase
) : BaseViewModel() {

    val mapDetailState: StateFlow<State> = flow {
        savedStateHandle.get<String>("food_recipe_id")?.toIntOrNull()?.let {
            emit(State.Success(getFoodRecipeMapDetailByIdUseCase(it)))
        } ?: emit(State.Success(FoodRecipeMapDetailUi.empty()))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = State.Loading,
    )

}