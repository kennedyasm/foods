package com.example.foods.common

import com.example.foods.core.ReadFileHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection

internal fun MockWebServer.enqueueOkHttpJsonResponse(jsonName: String) {
    val body = ReadFileHelper.readFileWithoutNewLineFromResources(jsonName)
    val mockResponse = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(body)
    this.enqueue(mockResponse)
}
