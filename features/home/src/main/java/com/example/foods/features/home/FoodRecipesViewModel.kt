package com.example.foods.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.State
import com.example.domain.State.Companion.to
import com.example.domain.models.FoodRecipeItemUi
import com.example.domain.models.FoodRecipeItemUi.Companion.isMatchingWithSearchQuery
import com.example.domain.usecases.GetFoodRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _hasSearchFocus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _foodRecipes: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()


    @OptIn(FlowPreview::class)
    val foodRecipes: StateFlow<State> = searchText
        .onEach { _isSearching.update { _hasSearchFocus.value } }
        .debounce(500L)
        .combine(_foodRecipes, ::filterQueryTextInState)
        .onEach { _isSearching.update { false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(0),
            initialValue = _foodRecipes.value,
        )

    init {
        getFoodRecipes()
    }

    fun hasSearchFocus(hasFocus: Boolean) {
        _hasSearchFocus.value = hasFocus
    }

    private fun filterQueryTextInState(queryText: String, foodRecipesState: State): State {
        return if (queryText.isNotBlank()) {
            when (foodRecipesState) {
                is State.Success -> {
                    State.Success(
                        foodRecipesState.to<List<FoodRecipeItemUi>>()
                            .filter { it.isMatchingWithSearchQuery(queryText) })
                }
                else -> foodRecipesState
            }
        } else {
            foodRecipesState
        }
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun refreshFoodRecipes() {
        _foodRecipes.value = State.Loading
        getFoodRecipes()
    }

    private fun getFoodRecipes() {
        getFoodRecipesUseCase.invoke()
            .subscribe(::successGetFoodRecipes, ::errorGetFoodRecipes)
            .also(disposables::add)
    }

    private fun successGetFoodRecipes(foodRecipes: List<FoodRecipeItemUi>) {
      _foodRecipes.value = State.Success(foodRecipes)
    }

    private fun errorGetFoodRecipes(throwable: Throwable) {
        _foodRecipes.value = State.Error(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
