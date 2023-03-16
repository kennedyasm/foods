package com.example.foods.core.extensions

import com.example.foods.core.files.ReadFileHelper
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

internal fun MockWebServer.enqueueForbiddenResponse() {
    val mockResponse = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_FORBIDDEN)
        .setBody("forbidden")
    this.enqueue(mockResponse)
}
