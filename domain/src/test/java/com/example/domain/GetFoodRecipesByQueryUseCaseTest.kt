package com.example.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.testing.given
import com.example.domain.doubles.provideFoodRecipeItemUiCaldoDePiedra
import com.example.domain.repository.FoodRecipesRepository
import com.example.domain.usecases.GetFoodRecipesByQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
class GetFoodRecipesByQueryUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesRepository: FoodRecipesRepository
    private lateinit var getFoodRecipesByQueryUseCase: GetFoodRecipesByQueryUseCase

    @Before
    fun setup() {
        getFoodRecipesByQueryUseCase =
            GetFoodRecipesByQueryUseCase(foodRecipesRepository)
    }

    @Test
    fun callGetFoodRecipesByQueryWhenGetFoodRecipesByQueryUseCaseIsExecuted() {
        getFoodRecipesByQueryUseCase("PIEDRA")

        verify(foodRecipesRepository, atMost(1)).getFoodRecipesByQuery("PIEDRA")
    }

    @Test
    fun assertDataWhenGetFoodRecipesByQueryIsExecutedMatchingQueryTextWithItemName() = runTest {
        val foodRecipeItemUiList = listOf(provideFoodRecipeItemUiCaldoDePiedra())
        val foodRecipeItemUiListFlow = flow { emit(foodRecipeItemUiList) }
        given(foodRecipesRepository.getFoodRecipesByQuery("PIEDRA"))
            .thenReturn(foodRecipeItemUiListFlow)

        val items = getFoodRecipesByQueryUseCase("PIEDRA").first()

        assertThat(items.size, equalTo(1))
        assertFoodRecipeItemUi(items.first())
    }
}
