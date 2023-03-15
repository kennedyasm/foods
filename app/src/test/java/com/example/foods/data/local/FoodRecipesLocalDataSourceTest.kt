package com.example.foods.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.asserts.assertFoodRecipeItemEntity
import com.example.foods.asserts.assertFoodRecipeItemEntityList
import com.example.foods.core.extensions.testAndGetData
import com.example.foods.core.given
import com.example.foods.data.local.dao.FoodRecipesDao
import com.example.foods.data.local.datasource.FoodRecipesLocalDataSource
import com.example.foods.data.local.datasource.FoodRecipesLocalDataSourceImpl
import com.example.foods.doubles.provideCaldoDeCamaronFoodRecipeItemEntity
import com.example.foods.doubles.provideFoodRecipeItemEntityList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FoodRecipesLocalDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesDao: FoodRecipesDao
    private lateinit var foodRecipesLocalDataSource: FoodRecipesLocalDataSource

    @Before
    fun setup() {
        foodRecipesLocalDataSource = FoodRecipesLocalDataSourceImpl(foodRecipesDao)
    }

    @Test
    fun callInsertWhenInsertFoodRecipesIsExecuted() {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(foodRecipesDao.insert(foodRecipeItemEntityList)).thenReturn(Completable.complete())

        foodRecipesLocalDataSource.insertFoodRecipes(foodRecipeItemEntityList).test()
            .assertNoErrors().assertComplete()

        verify(foodRecipesDao, atMost(1)).insert(foodRecipeItemEntityList)
    }

    @Test
    fun callGetAllWhenGetFoodRecipesIsExecuted() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(foodRecipesDao.getAll()).thenReturn(foodRecipeItemEntityList)

        foodRecipesLocalDataSource.getFoodRecipes()

        verify(foodRecipesDao, atMost(1)).getAll()
    }

    @Test
    fun assertDataFromGetAllWhenGetFoodRecipesIsExecuted() = runTest {
        val foodRecipeItemEntityList = provideFoodRecipeItemEntityList()
        given(foodRecipesDao.getAll()).thenReturn(foodRecipeItemEntityList)

        val items = foodRecipesLocalDataSource.getFoodRecipes()

        assertFoodRecipeItemEntityList(items)
    }

    @Test
    fun callGetByIdWhenGetFoodRecipeByIdIsExecuted() {
        foodRecipesLocalDataSource.getFoodRecipeById(2)

        verify(foodRecipesDao, atMost(1)).getById(2)
    }

    @Test
    fun assertDataFromGetByIdWhenGetFoodRecipeByIdIsExecuted() {
        val foodRecipeItemEntity = provideCaldoDeCamaronFoodRecipeItemEntity()
        given(foodRecipesDao.getById(2)).thenReturn(Single.just(foodRecipeItemEntity))

        val item = foodRecipesLocalDataSource.getFoodRecipeById(2).testAndGetData()

        assertFoodRecipeItemEntity(item)
    }

    @Test
    fun callDeleteWhenDeleteFoodRecipesIsExecuted() {
        given(foodRecipesDao.delete()).thenReturn(Completable.complete())

        foodRecipesLocalDataSource.deleteFoodRecipes().test().assertNoErrors().assertComplete()

        verify(foodRecipesDao, atMost(1)).delete()
    }
}
