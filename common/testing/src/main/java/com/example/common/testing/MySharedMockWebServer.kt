package com.example.common.testing

import okhttp3.mockwebserver.MockWebServer

object MySharedMockWebServer {
    private val mMockWebServer: MockWebServer = MockWebServer()
    val mockWebServer get() = mMockWebServer
}
