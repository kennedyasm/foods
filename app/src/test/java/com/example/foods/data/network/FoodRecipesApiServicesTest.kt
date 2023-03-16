package com.example.foods.data.network

import com.example.foods.asserts.assertGetFoodRecipesResponseDto
import com.example.foods.core.ApiServicesTest
import com.example.foods.core.extensions.completeAssertedTest
import com.example.foods.core.extensions.testAndGetData
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
