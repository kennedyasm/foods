package com.example.foods.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.foods.core.testing.MainCoroutineRule
import com.example.foods.core.testing.doubles.provideFoodRecipeDetailsUi
import com.example.foods.core.testing.given
import com.example.foods.domain.State
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.atMostOnce
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FoodRecipeDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Mock
    private lateinit var getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var foodRecipeDetailsViewModel: FoodRecipeDetailsViewModel

    @Before
    fun setup() {
        foodRecipeDetailsViewModel =
            FoodRecipeDetailsViewModel(savedStateHandle, getFoodRecipeDetailsByIdUseCase)
    }

    @Test
    fun callGetFoodRecipeDetailsByUseCaseWhenGetFoodDetailsByIdIsExecuted() = runTest {
        val foodRecipeDetailsUi = provideFoodRecipeDetailsUi()
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("2")
        given(getFoodRecipeDetailsByIdUseCase(2)).thenReturn(foodRecipeDetailsUi)

        foodRecipeDetailsViewModel.foodRecipeDetails.first()

        verify(getFoodRecipeDetailsByIdUseCase, atMostOnce()).invoke(2)
    }

    @Test
    fun assertDataWhenGetFoodDetailsByIdIsExecuted() = runTest {
        val foodRecipeDetailsUi = provideFoodRecipeDetailsUi()
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("2")
        given(getFoodRecipeDetailsByIdUseCase(2)).thenReturn(foodRecipeDetailsUi)

        val state = foodRecipeDetailsViewModel.foodRecipeDetails.first()

        assertThat(state, equalTo(State.Success(foodRecipeDetailsUi)))
    }

    @Test
    fun assertDataErrorWhenGetFoodDetailsByIdIsExecuted() = runTest {
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("")

        val state = foodRecipeDetailsViewModel.foodRecipeDetails.first()

        assertThat((state as State.Error).message, equalTo("No se encontró más información acerca de esta receta"))
    }
}
