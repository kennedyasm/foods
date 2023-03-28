package com.example.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.testing.given
import com.example.core.testing.testAndGetData
import com.example.domain.doubles.provideFoodRecipeDetails
import com.example.domain.repository.FoodRecipesRepository
import com.example.domain.usecases.GetFoodRecipeDetailsByIdUseCase
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

    @Test
    fun callGetFoodRecipeDetailsByIdWhenGetFoodRecipeDetailsIsExecuted() {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(foodRecipesRepository.getFoodRecipeDetailsById(2)).thenReturn(Single.just(foodRecipeDetailsUi))

        getFoodRecipeDetailsByIdUseCase(2)

        verify(foodRecipesRepository, atMost(1)).getFoodRecipeDetailsById(2)
    }

    @Test
    fun assertDataWhenGetFoodRecipeDetailsUseCaseIsExecuted() {
        val foodRecipeDetailsUi = provideFoodRecipeDetails()
        given(foodRecipesRepository.getFoodRecipeDetailsById(2)).thenReturn(Single.just(foodRecipeDetailsUi))

        val item = getFoodRecipeDetailsByIdUseCase(2).testAndGetData()

        assertFoodRecipeDetailsUi(item)
    }
}
