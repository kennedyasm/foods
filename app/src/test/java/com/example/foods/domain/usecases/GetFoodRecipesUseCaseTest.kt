package com.example.foods.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.asserts.assertFoodRecipeItemUiList
import com.example.foods.core.RxSchedulersImplTest
import com.example.foods.core.extensions.testAndGetData
import com.example.foods.core.given
import com.example.foods.core.rx.RxSchedulers
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.doubles.provideFoodRecipeItemUiList
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
    private val scheduler: RxSchedulers = RxSchedulersImplTest()
    private lateinit var getFoodRecipesUseCase: GetFoodRecipesUseCase

    @Before
    fun setup() {
        getFoodRecipesUseCase = GetFoodRecipesUseCase(foodRecipesRepository, scheduler)
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
