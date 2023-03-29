package com.example.foods.core.testing

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection

fun MockWebServer.enqueueOkHttpJsonResponse(jsonName: String) {
    val body = ReadFileHelper.readFileWithoutNewLineFromResources(jsonName)
    val mockResponse = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(body)
    this.enqueue(mockResponse)
}