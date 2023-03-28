package com.example.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.core.testing.RxSchedulersImplTest
import com.example.foods.core.testing.given
import com.example.foods.core.testing.testAndGetData
import com.example.data.asserts.assertFoodRecipeDetailsUi
import com.example.data.asserts.assertFoodRecipeItemUi
import com.example.data.asserts.assertFoodRecipeItemUiList
import com.example.data.doubles.FoodRecipeItemEntityDoubles.provideCaldoDePiedraFoodRecipeItemEntity
import com.example.data.doubles.FoodRecipeItemEntityDoubles.provideFoodRecipeItemEntityList
import com.example.data.doubles.FoodRecipesDtoDoubles.provideFoodRecipesResponseDto
import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.data.repository.FoodRecipesRepositoryImpl
import com.example.domain.repository.FoodRecipesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
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

    private val schedulers = RxSchedulersImplTest()
    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var foodRecipesRepository: FoodRecipesRepository

    @Before
    fun setup() {
        foodRecipesRepository =
            FoodRecipesRepositoryImpl(networkDataSource, localDataSource, schedulers, dispatcher)
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
    fun callGetFoodRecipeByIdWhenGetFoodRecipeDetailsByIdIsExecuted() = runTest {
        val foodRecipeItemEntity = provideCaldoDePiedraFoodRecipeItemEntity()
        given(localDataSource.getFoodRecipeById(2)).thenReturn(foodRecipeItemEntity)

        foodRecipesRepository.getFoodRecipeDetailsById(2)

        verify(localDataSource, atMost(1)).getFoodRecipeById(2)
    }

    @Test
    fun assertDataWhenGetFoodRecipeDetailsByIdIsExecuted() = runTest {
        val foodRecipeItemEntity = provideCaldoDePiedraFoodRecipeItemEntity()
        given(localDataSource.getFoodRecipeById(2)).thenReturn(foodRecipeItemEntity)

        val item = foodRecipesRepository.getFoodRecipeDetailsById(2)

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
