package com.example.foods.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.domain.asserts.assertFoodRecipeDetailsUi
import com.example.foods.domain.doubles.provideFoodRecipeDetails
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.domain.usecases.GetFoodRecipeDetailsByIdUseCase
import com.example.foods.core.testing.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.atMost
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFoodRecipeDetailsByIdUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesRepository: FoodRecipesRepository

    private lateinit var getFoodRecipeDetailsByIdUseCase: GetFoodRecipeDetailsByIdUseCase

    @Before
    fun setup() {
        getFoodRecipeDetailsByIdUseCase =
            GetFoodRecipeDetailsByIdUseCase(foodRecipesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun callGetFoodRecipeDetailsByIdWhenGetFoodRecipeDetailsIsExecuted() = runTest {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(foodRecipesRepository.getFoodRecipeDetailsById(2)).thenReturn(foodRecipeDetailsUi)

        getFoodRecipeDetailsByIdUseCase(2)

        verify(foodRecipesRepository, atMost(1)).getFoodRecipeDetailsById(2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun assertDataWhenGetFoodRecipeDetailsUseCaseIsExecuted() = runTest {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(foodRecipesRepository.getFoodRecipeDetailsById(2)).thenReturn(foodRecipeDetailsUi)

        val item = getFoodRecipeDetailsByIdUseCase(2)

        assertFoodRecipeDetailsUi(item)
    }
}
