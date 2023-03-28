package com.example.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.testing.given
import com.example.core.testing.testAndGetData
import com.example.data.network.core.assertGetFoodRecipesResponseDto
import com.example.data.network.doubles.provideFoodRecipesResponseDto
import com.example.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.data.network.datasource.FoodRecipesNetworkDataSourceImpl
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
