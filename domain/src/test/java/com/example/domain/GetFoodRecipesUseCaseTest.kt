package com.example.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.testing.given
import com.example.common.testing.testAndGetData
import com.example.domain.doubles.provideFoodRecipeItemUiList
import com.example.domain.repository.FoodRecipesRepository
import com.example.domain.usecases.GetFoodRecipesUseCase
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
class GetFoodRecipesUseCaseTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesRepository: FoodRecipesRepository
    private lateinit var getFoodRecipesUseCase: GetFoodRecipesUseCase

    @Before
    fun setup() {
        getFoodRecipesUseCase = GetFoodRecipesUseCase(foodRecipesRepository)
    }

    @Test
    fun callGetFoodRecipesWhenGetFoodRecipesUseCaseIsExecuted() {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()
        given(foodRecipesRepository.getFoodRecipes()).thenReturn(Single.just(foodRecipeItemUiList))
        getFoodRecipesUseCase()

        verify(foodRecipesRepository, atMost(1)).getFoodRecipes()
    }

    @Test
    fun assertDataWhenGetFoodRecipesUseCaseIsExecuted() {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()
        given(foodRecipesRepository.getFoodRecipes()).thenReturn(Single.just(foodRecipeItemUiList))

        val items = getFoodRecipesUseCase.invoke().testAndGetData()

        assertFoodRecipeItemUiList(items)
    }
}
