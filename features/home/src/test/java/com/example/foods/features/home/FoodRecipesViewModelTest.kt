package com.example.foods.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.core.testing.MainCoroutineRule
import com.example.foods.core.testing.doubles.provideFoodRecipeItemUiList
import com.example.foods.core.testing.given
import com.example.foods.domain.State
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.domain.usecases.GetFoodRecipesUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
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

    private lateinit var foodRecipesViewModel: FoodRecipesViewModel

    @Before
    fun setup() {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()
        given(getFoodRecipesUseCase.invoke()).thenReturn(Single.just(foodRecipeItemUiList))
        foodRecipesViewModel = FoodRecipesViewModel(getFoodRecipesUseCase)
    }

    @Test
    fun callGetFoodRecipesUseCaseWhenGetFoodRecipesIsExecutedOnInit() {
        verify(getFoodRecipesUseCase, atMost(1)).invoke()
    }

    @Test
    fun assertDataLoadingWhenGetFoodRecipesIsExecutedOnInit() = runTest {
        val getFoodRecipeStateValue = foodRecipesViewModel.foodRecipes

        val state = getFoodRecipeStateValue.first()
        assertThat(state, equalTo(State.Loading))
    }

    @OptIn(FlowPreview::class)
    @Test
    fun assertDataWhenGetFoodRecipesIsExecutedOnInit() = runTest {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()

        val getFoodRecipeStateValue = foodRecipesViewModel.foodRecipes

        val state = getFoodRecipeStateValue.debounce(FOOD_RECIPES_DEBOUNCE).first()
        assertThat(state, equalTo(State.Success(foodRecipeItemUiList)))
    }

    @OptIn(FlowPreview::class)
    @Test
    fun assertDataWhenOnSearchTextChangedIsExecuted() = runTest {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()

        foodRecipesViewModel.hasSearchFocus(true)
        foodRecipesViewModel.onSearchTextChanged("CALDO DE PIEDRA")

        val items = foodRecipesViewModel.foodRecipes.debounce(FOOD_RECIPES_DEBOUNCE)

        assertThat(items.first(), equalTo(State.Success(listOf(foodRecipeItemUiList.component2()))))
    }

    @OptIn(FlowPreview::class)
    @Test
    fun assertDataWhenOnSearchTextChangedIsExecutedButEmptyResults() = runTest {
        foodRecipesViewModel.hasSearchFocus(true)
        foodRecipesViewModel.onSearchTextChanged("c4ld0d3pi3dr4")

        val items = foodRecipesViewModel.foodRecipes.debounce(FOOD_RECIPES_DEBOUNCE)

        assertThat(items.first(), equalTo(State.Success(emptyList<FoodRecipeItemUi>())))
    }

    @OptIn(FlowPreview::class)
    @Test
    fun assertDataWhenRefreshFoodRecipesIsExecuted() = runTest {
        val foodRecipeItemUiList = provideFoodRecipeItemUiList()
        given(getFoodRecipesUseCase.invoke()).thenReturn(Single.just(foodRecipeItemUiList))

        foodRecipesViewModel.refreshFoodRecipes()

        val items = foodRecipesViewModel.foodRecipes.debounce(FOOD_RECIPES_DEBOUNCE).first()

        assertThat(items, equalTo(State.Success(foodRecipeItemUiList)))
    }

    @OptIn(FlowPreview::class)
    @Test
    fun assertDataErrorWhenRefreshFoodRecipesIsExecutedButIsError() = runTest {
        val error = Throwable("some error")
        given(getFoodRecipesUseCase.invoke()).thenReturn(Single.error(error))

        foodRecipesViewModel.refreshFoodRecipes()
        val items = foodRecipesViewModel.foodRecipes.debounce(FOOD_RECIPES_DEBOUNCE).first()

        assertThat(items, equalTo(State.Error(error)))
    }

    companion object {
        const val FOOD_RECIPES_DEBOUNCE = 600L
    }
}
