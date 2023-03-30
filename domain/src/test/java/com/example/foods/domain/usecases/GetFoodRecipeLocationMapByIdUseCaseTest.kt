package com.example.foods.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.core.testing.given
import com.example.foods.domain.asserts.assertFoodRecipeLocationMapUi
import com.example.foods.domain.doubles.provideFoodRecipeLocationMapUi
import com.example.foods.domain.repository.FoodRecipesRepository
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
class GetFoodRecipeLocationMapByIdUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesRepository: FoodRecipesRepository

    private lateinit var getFoodRecipeLocationMapByIdUseCase: GetFoodRecipeLocationMapByIdUseCase

    @Before
    fun setup() {
        getFoodRecipeLocationMapByIdUseCase =
            GetFoodRecipeLocationMapByIdUseCase(foodRecipesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun callGetFoodRecipeMapDetailByIdWhenGetFoodRecipeLocationMapByIdUseCaseIsExecuted() {
        val foodRecipeLocationMapUi = provideFoodRecipeLocationMapUi()
        runTest {

            given(foodRecipesRepository.getFoodRecipeLocationMapById(2))
                .thenReturn(foodRecipeLocationMapUi)

            getFoodRecipeLocationMapByIdUseCase(2)

            verify(foodRecipesRepository, atMost(1)).getFoodRecipeDetailsById(2)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun assertDataWhenGetFoodRecipeLocationMapByIdUseCaseIsExecuted() {
        val foodRecipeLocationMapUi = provideFoodRecipeLocationMapUi()
        runTest {

            given(foodRecipesRepository.getFoodRecipeLocationMapById(2))
                .thenReturn(foodRecipeLocationMapUi)

            val item = getFoodRecipeLocationMapByIdUseCase(2)

            assertFoodRecipeLocationMapUi(item)
        }
    }
}
