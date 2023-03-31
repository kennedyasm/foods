package com.example.foods.data.source.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.core.testing.given
import com.example.foods.core.testing.testAndGetData
import com.example.foods.data.source.network.asserts.assertGetFoodRecipesResponseDto
import com.example.foods.data.source.network.doubles.provideFoodRecipesResponseDto
import com.example.foods.data.source.network.api.FoodRecipesApiServices
import com.example.foods.data.source.network.datasource.FoodRecipesNetworkDataSource
import com.example.foods.data.source.network.datasource.FoodRecipesNetworkDataSourceImpl
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
class FoodRecipesNetworkDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var foodRecipesApiServices: FoodRecipesApiServices
    private lateinit var foodRecipesNetworkDataSource: FoodRecipesNetworkDataSource

    @Before
    fun setup() {
        foodRecipesNetworkDataSource = FoodRecipesNetworkDataSourceImpl(foodRecipesApiServices)
    }

    @Test
    fun callGetFoodRecipesWhenGetFoodRecipesIsExecuted() {
        val foodRecipesResponseDto = provideFoodRecipesResponseDto()
        given(foodRecipesApiServices.getFoodRecipes()).thenReturn(Single.just(foodRecipesResponseDto))

        foodRecipesNetworkDataSource.getFoodRecipes()

        verify(foodRecipesApiServices, atMost(1)).getFoodRecipes()
    }

    @Test
    fun assertDataWhenGetFoodRecipesIsExecuted() {
        val foodRecipesResponseDto = provideFoodRecipesResponseDto()
        given(foodRecipesApiServices.getFoodRecipes()).thenReturn(Single.just(foodRecipesResponseDto))

        val data = foodRecipesNetworkDataSource.getFoodRecipes().testAndGetData()
        assertGetFoodRecipesResponseDto(data)
    }
}
