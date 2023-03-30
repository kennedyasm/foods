package com.example.foods.feature.location

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.foods.core.testing.MainCoroutineRule
import com.example.foods.core.testing.doubles.provideFoodRecipeLocationMapUi
import com.example.foods.core.testing.given
import com.example.foods.domain.State
import com.example.foods.domain.usecases.GetFoodRecipeLocationMapByIdUseCase
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
class FoodRecipeLocationMapViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Mock
    private lateinit var getFoodRecipeLocationMapByIdUseCase: GetFoodRecipeLocationMapByIdUseCase

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var foodRecipeLocationMapViewModel: FoodRecipeLocationMapViewModel

    @Before
    fun setup() {
        foodRecipeLocationMapViewModel =
            FoodRecipeLocationMapViewModel(savedStateHandle, getFoodRecipeLocationMapByIdUseCase)
    }

    @Test
    fun callGetFoodRecipeDetailsByUseCaseWhenGetFoodDetailsByIdIsExecuted() = runTest {
        val foodRecipeLocationMapUi = provideFoodRecipeLocationMapUi()
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("2")
        given(getFoodRecipeLocationMapByIdUseCase(2)).thenReturn(foodRecipeLocationMapUi)

        foodRecipeLocationMapViewModel.locationMap.first()

        verify(getFoodRecipeLocationMapByIdUseCase, atMostOnce()).invoke(2)
    }

    @Test
    fun assertDataWhenGetFoodDetailsByIdIsExecuted() = runTest {
        val foodRecipeLocationMapUi = provideFoodRecipeLocationMapUi()
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("2")
        given(getFoodRecipeLocationMapByIdUseCase(2)).thenReturn(foodRecipeLocationMapUi)

        val state = foodRecipeLocationMapViewModel.locationMap.first()

        assertThat(state, equalTo(State.Success(foodRecipeLocationMapUi)))
    }

    @Test
    fun assertDataErrorWhenGetFoodDetailsByIdIsExecuted() = runTest {
        given(savedStateHandle.get<String>("food_recipe_id")).thenReturn("")

        val state = foodRecipeLocationMapViewModel.locationMap.first()

        assertThat((state as State.Error).message, equalTo("no location map information"))
    }
}
