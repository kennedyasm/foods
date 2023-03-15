package com.example.foods.core

import okhttp3.mockwebserver.MockWebServer

object MySharedMockWebServer {
    private val mMockWebServer: MockWebServer = MockWebServer()
    val mockWebServer get() = mMockWebServer
}
