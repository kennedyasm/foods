package com.example.data.source.network

import com.example.data.source.network.asserts.assertGetFoodRecipesResponseDto
import com.example.data.source.network.base.ApiServicesTest
import com.example.foods.core.testing.completeAssertedTest
import com.example.foods.core.testing.enqueueOkHttpJsonResponse
import com.example.foods.core.testing.testAndGetData
import com.example.foods.data.source.network.api.FoodRecipesApiServices
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FoodRecipesApiServicesTest : ApiServicesTest() {

    private lateinit var foodRecipesApiServices: FoodRecipesApiServices

    override fun setup() {
        super.setup()
        foodRecipesApiServices = retrofitHelper.createService(FoodRecipesApiServices::class.java)
    }

    @Test
    fun assertApiKeyQueryParamWhenGetFoodRecipesIsExecuted() {
        mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")
        foodRecipesApiServices.getFoodRecipes().completeAssertedTest()

        val apiKey = mockWebServer.takeRequest().requestUrl?.queryParameter("api_key")

        assertThat(apiKey, equalTo("539e656504e290ada23ef45c310952f2"))
    }

    @Test
    fun assertDataWhenGetFoodRecipesServiceIsExecuted() {
        mockWebServer.enqueueOkHttpJsonResponse("food_recipes_response.json")

        val dtoResponse = foodRecipesApiServices.getFoodRecipes().testAndGetData()

        assertGetFoodRecipesResponseDto(dtoResponse)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}
