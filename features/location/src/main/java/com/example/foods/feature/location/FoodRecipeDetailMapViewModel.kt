package com.example.foods.feature.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foods.domain.State
import com.example.foods.domain.usecases.GetFoodRecipeLocationMapByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoodRecipeDetailMapViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodRecipeLocationMapByIdUseCase: GetFoodRecipeLocationMapByIdUseCase
) : ViewModel() {

    val mapDetailState: StateFlow<State> = flow {
        savedStateHandle.get<String>(RECIPE_ID)?.toIntOrNull()?.let {
            emit(State.Success(getFoodRecipeLocationMapByIdUseCase(it)))
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
