package com.example.data.network

import com.example.common.testing.completeAssertedTest
import com.example.common.testing.enqueueOkHttpJsonResponse
import com.example.common.testing.testAndGetData
import com.example.data.network.base.ApiServicesTest
import com.example.data.network.core.assertGetFoodRecipesResponseDto
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
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
}
