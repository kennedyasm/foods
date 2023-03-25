package com.example.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.testing.given
import com.example.common.testing.testAndGetData
import com.example.data.local.asserts.assertFoodRecipeItemEntity
import com.example.data.local.asserts.assertFoodRecipeItemEntityList
import com.example.data.local.dao.FoodRecipesDao
import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.local.datasource.FoodRecipesLocalDataSourceImpl
import com.example.data.local.doubles.provideCaldoDePiedraFoodRecipeItemEntity
import com.example.data.local.doubles.provideFoodRecipeItemEntityList
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
    fun assertDataWhenGetFoodRecipesIsExecuted() = runTest {
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
    fun assertDataWhenGetFoodRecipeByIdIsExecuted() {
        val foodRecipeItemEntity = provideCaldoDePiedraFoodRecipeItemEntity()
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
