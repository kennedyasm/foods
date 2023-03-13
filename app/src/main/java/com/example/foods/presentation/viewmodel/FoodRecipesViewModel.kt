package com.example.foods.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foods.core.State
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.usecases.GetFoodRecipesByQueryUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class FoodRecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase,
    private val getFoodRecipesByQueryUseCase: GetFoodRecipesByQueryUseCase
) : BaseViewModel() {

    private val _getFoodRecipesState: MutableLiveData<State> = MutableLiveData()
    val getFoodRecipesState: LiveData<State>
        get() = _getFoodRecipesState

    fun getFoodRecipes() {
        _getFoodRecipesState.value = State.Loading
        getFoodRecipesUseCase.invoke()
            .subscribe(::successGetFoodRecipes, ::errorGetFoodRecipes)
            .also(disposables::add)
    }

    private fun successGetFoodRecipes(foodRecipes: List<FoodRecipeItemUi>) {
        _getFoodRecipesState.value = State.Success(foodRecipes)
    }

    private fun errorGetFoodRecipes(throwable: Throwable) {
        _getFoodRecipesState.value = State.Error(throwable)
    }

    fun getFoodRecipesByQuery(query: String): Flow<List<FoodRecipeItemUi>> {
        return getFoodRecipesByQueryUseCase(query)
    }

    private val _query: MutableLiveData<String> = MutableLiveData()

    val query: LiveData<String> get() = _query

    fun searchFoodRecipesByQuery(query: String = "") {

        _query.value = query
    }
}
