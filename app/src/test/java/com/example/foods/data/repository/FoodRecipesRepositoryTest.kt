package com.example.foods.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.asserts.assertFoodRecipeDetailsUi
import com.example.foods.asserts.assertFoodRecipeItemUi
import com.example.foods.asserts.assertFoodRecipeItemUiList
import com.example.foods.core.extensions.testAndGetData
import com.example.foods.core.given
import com.example.foods.data.local.datasource.FoodRecipesLocalDataSource
import com.example.foods.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.foods.data.toFoodRecipeItemEntityList
import com.example.foods.domain.repository.FoodRecipesRepository
import com.example.foods.doubles.provideCaldoDePiedraFoodRecipeItemEntity
import com.example.foods.doubles.provideFoodRecipeItemEntityList
import com.example.foods.doubles.provideFoodRecipesResponseDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class FoodRecipesRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var localDataSource: FoodRecipesLocalDataSource

    @Mock
    lateinit var networkDataSource: FoodRecipesNetworkDataSource
    private lateinit var foodRecipesRepository: FoodRecipesRepository

    @Before
    fun setup() {
        foodRecipesRepository = FoodRecipesRepositoryImpl(networkDataSource, localDataSource)
    }

    @Test
    fun callDeleteFoodRecipesWhenGetFoodRecipesIsExecuted() {
        val getFoodRecipesResponseDto = provideFoodRecipesResponseDto()
        given(localDataSource.deleteFoodRecipes()).thenReturn(Completable.complete())
        given(networkDataSource.getFoodRecipes()).thenReturn(Single.just(getFoodRecipesResponseDto))

        foodRecipesRepository.getFoodRecipes()

        verify(localDataSource, atMost(1)).deleteFoodRecipes()
    }

    @Test
    fun callGetFoodRecipesWhenGetFoodRecipesIsExecuted() {
        val getFoodRecipesResponseDto = provideFoodRecipesResponseDto()
        given(localDataSource.deleteFoodRecipes()).thenReturn(Completable.complete())
        given(networkDataSource.getFoodRecipes()).thenReturn(Single.just(getFoodRecipesResponseDto))

        foodRecipesRepository.getFoodRecipes()

        verify(networkDataSource, atMost(1)).getFoodRecipes()
    }

    @Test
    fun callInsertFoodRecipesWhenGetFoodRecipesIsExecuted() {
        val getFoodRecipesResponseDto = provideFoodRecipesResponseDto()
        given(localDataSource.deleteFoodRecipes()).thenReturn(Completable.complete())
        given(networkDataSource.getFoodRecipes()).thenReturn(Single.just(getFoodRecipesResponseDto))

        foodRecipesRepository.getFoodRecipes()

        verify(networkDataSource, atMost(1)).getFoodRecipes()
    }

    @Test
    fun assertDataWhenGetFoodRecipesIsExecuted() {
        val getFoodRecipesResponseDto = provideFoodRecipesResponseDto()
        val foodRecipeItemEntityList = getFoodRecipesResponseDto.toFoodRecipeItemEntityList()
        given(localDataSource.deleteFoodRecipes()).thenReturn(Completable.complete())
        given(networkDataSource.getFoodRecipes()).thenReturn(Single.just(getFoodRecipesResponseDto))
        given(localDataSource.insertFoodRecipes(foodRecipeItemEntityList)).thenReturn(Completable.complete())

        val items = foodRecipesRepository.getFoodRecipes().testAndGetData()

        assertFoodRecipeItemUiList(items)
    }

    @Test
    fun callGetFoodRecipeByIdWhenGetFoodRecipeDetailsByIdIsExecuted() {
        val foodRecipeItemEntity = provideCaldoDePiedraFoodRecipeItemEntity()
        given(localDataSource.getFoodRecipeById(2)).thenReturn(Single.just(foodRecipeItemEntity))

        foodRecipesRepository.getFoodRecipeDetailsById(2)

        verify(localDataSource, atMost(1)).getFoodRecipeById(2)
    }

    @Test
    fun assertDataFromGetFoodRecipeByIdWhenGetFoodRecipeDetailsByIdIsExecuted() {
        val foodRecipeItemEntity = provideCaldoDePiedraFoodRecipeItemEntity()
        given(localDataSource.getFoodRecipeById(2)).thenReturn(Single.just(foodRecipeItemEntity))

        val item = foodRecipesRepository.getFoodRecipeDetailsById(2).testAndGetData()

        assertFoodRecipeDetailsUi(item)
    }

    @Test
    fun callGetFoodRecipesWhenGetFoodRecipesByQueryIsExecuted() = runTest {
        foodRecipesRepository.getFoodRecipesByQuery("PIEDRA")

        verify(localDataSource, atMost(1)).getFoodRecipes()
    }

    @Test
    fun assertDataWhenGetFoodRecipesByQueryIsExecutedMatchingQueryTextWithItemName() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(localDataSource.getFoodRecipes()).thenReturn(foodRecipeItemEntityList)

        val item = foodRecipesRepository.getFoodRecipesByQuery("PIEDRA").first()

        assertThat(item.size, equalTo(1))
        assertFoodRecipeItemUi(item.first())
    }

    @Test
    fun assertDataWhenGetFoodRecipesByQueryIsExecutedMatchingQueryTextWithIngredients() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(localDataSource.getFoodRecipes()).thenReturn(foodRecipeItemEntityList)

        val item = foodRecipesRepository.getFoodRecipesByQuery("cucharadas").first()

        assertThat(item.size, equalTo(2))
    }

    @Test
    fun assertDataWhenGetFoodRecipesByQueryIsExecutedNoMatchingQueryText() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(localDataSource.getFoodRecipes()).thenReturn(foodRecipeItemEntityList)

        val item = foodRecipesRepository.getFoodRecipesByQuery("searchText").first()

        assertThat(item.size, equalTo(0))
    }

    @Test
    fun callDeleteFoodRecipesWhenDeleteFoodRecipesIsExecuted() {
        foodRecipesRepository.deleteFoodRecipes()

        verify(localDataSource, atMost(1)).deleteFoodRecipes()
    }
}
