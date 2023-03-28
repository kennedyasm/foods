package com.example.foods.feature.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
) : ViewModel() {

    val mapDetailState: StateFlow<State> = flow {
        savedStateHandle.get<String>("food_recipe_id")?.toIntOrNull()?.let {
            emit(State.Success(getFoodRecipeMapDetailByIdUseCase(it)))
        } ?: emit(State.Success(FoodRecipeMapDetailUi.empty()))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = State.Loading,
    )

}