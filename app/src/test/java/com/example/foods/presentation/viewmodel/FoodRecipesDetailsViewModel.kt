package com.example.foods.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foods.core.State
import com.example.foods.core.given
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import com.example.foods.doubles.provideFoodRecipeDetails
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.atMost
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FoodRecipesDetailsViewModel {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase
    private lateinit var foodRecipeDetailsViewModel: FoodRecipeDetailsViewModel
    @Mock  lateinit var observer: Observer<State>

    @Before
    fun setup() {
        foodRecipeDetailsViewModel = FoodRecipeDetailsViewModel(getFoodRecipeDetailsByIdUseCase)
    }

    @Test
    fun callGetFoodRecipeDetailsByUseCaseWhenGetFoodDetailsByIdIsExecuted() {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(getFoodRecipeDetailsByIdUseCase.invoke(2)).thenReturn(Single.just(foodRecipeDetailsUi))

        foodRecipeDetailsViewModel.getFoodRecipeDetailsById(2)


        verify(getFoodRecipeDetailsByIdUseCase, atMost(1)).invoke(2)
    }

    @Test
    fun verifyObserverStateChangedToLoadingWhenGetFoodDetailsByIdIsExecuted() {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(getFoodRecipeDetailsByIdUseCase.invoke(2)).thenReturn(Single.just(foodRecipeDetailsUi))

        foodRecipeDetailsViewModel.getFoodRecipeDetails.observeForever(observer)
        foodRecipeDetailsViewModel.getFoodRecipeDetailsById(2)

        foodRecipeDetailsViewModel.getFoodRecipeDetails

        verify(observer, atMost(1)).onChanged(State.Loading)
    }

    @Test
    fun verifyObserverStateChangedToSuccessWhenGetFoodDetailsByIdIsExecuted() {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(getFoodRecipeDetailsByIdUseCase.invoke(2)).thenReturn(Single.just(foodRecipeDetailsUi))

        foodRecipeDetailsViewModel.getFoodRecipeDetails.observeForever(observer)
        foodRecipeDetailsViewModel.getFoodRecipeDetailsById(2)

        verify(observer, atMost(1)).onChanged(State.Success(foodRecipeDetailsUi))
    }

    @Test
    fun verifyObserverStateChangedToErrorWhenGetFoodDetailsByIdIsExecuted() {
        val throwable = Throwable("some error")
        given(getFoodRecipeDetailsByIdUseCase.invoke(2)).thenReturn(Single.error(throwable))

        foodRecipeDetailsViewModel.getFoodRecipeDetails.observeForever(observer)
        foodRecipeDetailsViewModel.getFoodRecipeDetailsById(2)

        foodRecipeDetailsViewModel.getFoodRecipeDetails

        verify(observer, atMost(1)).onChanged(State.Error(throwable))
    }
}
