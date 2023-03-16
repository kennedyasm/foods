package com.example.foods.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.core.MainCoroutineRule
import com.example.foods.core.State
import com.example.foods.core.State.Companion.to
import com.example.foods.core.given
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.usecases.GetFoodRecipesByQueryUseCase
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import com.example.foods.doubles.provideFoodRecipeItemUiCaldoDePiedra
import com.example.foods.doubles.provideFoodRecipeItemUiList
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.atMost
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FoodRecipesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Mock
    private lateinit var getFoodRecipesUseCase: GetFoodRecipesUseCase

    @Mock
    private lateinit var getFoodRecipesByQueryUseCase: GetFoodRecipesByQueryUseCase
    private lateinit var foodRecipesViewModel: FoodRecipesViewModel

    @Before
    fun setup() {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()
        given(getFoodRecipesUseCase.invoke()).thenReturn(Single.just(foodRecipeItemUiList))
        foodRecipesViewModel =
            FoodRecipesViewModel(getFoodRecipesUseCase, getFoodRecipesByQueryUseCase)
    }

    @Test
    fun callGetFoodRecipesUseCaseWhenGetFoodRecipesIsExecutedOnInit() {
        verify(getFoodRecipesUseCase, atMost(1)).invoke()
    }

    @Test
    fun assertDataWhenGetFoodRecipesIsExecutedOnInit() = runTest {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()

        val getFoodRecipeStateValue = foodRecipesViewModel.getFoodRecipesState

        assertThat(getFoodRecipeStateValue.first(), equalTo(State.Success(foodRecipeItemUiList)))
    }

    @Test
    fun assertErrorMessageWhenGetFoodRecipesIsExecuted() = runTest {
        val throwable = Throwable("some error")
        given(getFoodRecipesUseCase.invoke()).thenReturn(Single.error(throwable))

        foodRecipesViewModel.getFoodRecipes()

        val getFoodRecipeStateValue = foodRecipesViewModel.getFoodRecipesState

        val stateErrorMessage = (getFoodRecipeStateValue.first() as State.Error).message
        assertThat(stateErrorMessage, equalTo("Error: some error, intenta más tarde."))
    }

    @Test
    fun callGetFoodWhenRecipesByQueryUseCaseIsExecuted() = runTest {
        val foodRecipeItemUiList = listOf(provideFoodRecipeItemUiCaldoDePiedra())
        val flow = flow {
            emit(foodRecipeItemUiList)
        }
        given(getFoodRecipesByQueryUseCase.invoke("CALDO DE PIEDRA")).thenReturn(flow)

        foodRecipesViewModel.getFoodRecipesByQuery("CALDO DE PIEDRA")

        verify(getFoodRecipesByQueryUseCase, atMost(1)).invoke("CALDO DE PRIEDRA")
    }

    @Test
    fun assertDataWhenRecipesByQueryUseCaseIsExecuted() = runTest {
        val foodRecipeItemUiList = listOf(provideFoodRecipeItemUiCaldoDePiedra())
        val flow = flow { emit(foodRecipeItemUiList) }
        given(getFoodRecipesByQueryUseCase.invoke("CALDO DE PIEDRA")).thenReturn(flow)

        foodRecipesViewModel.getFoodRecipesByQuery("CALDO DE PIEDRA")

        val state = foodRecipesViewModel.getFoodRecipesState.value
        val successState = state as State.Success

        assertThat(successState.to<List<FoodRecipeItemUi>>().size, equalTo(1))
    }
}
