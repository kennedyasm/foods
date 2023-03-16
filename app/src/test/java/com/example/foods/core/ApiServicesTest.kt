package com.example.foods.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.BuildConfig
import com.example.foods.core.files.ReadFileHelper
import com.example.foods.core.network.auth.AuthInterceptor
import com.example.foods.core.network.retrofit.RetrofitHelper
import com.example.foods.core.network.retrofit.RetrofitHelperImpl
import okhttp3.Interceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.net.HttpURLConnection

open class ApiServicesTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private var authInterceptor: Interceptor = AuthInterceptor()
    protected val mockWebServer: MockWebServer = MockWebServer()
    protected lateinit var retrofitHelper: RetrofitHelper

    @Before
    open fun setup() {
        val httpUrl = mockWebServer.url(BuildConfig.SERVICES_PATH)
        retrofitHelper = RetrofitHelperImpl(httpUrl, listOf(authInterceptor))
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    companion object {
        internal fun MockWebServer.enqueueOkHttpJsonResponse(jsonName: String) {
            val body = ReadFileHelper.readFileWithoutNewLineFromResources(jsonName)
            val mockResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(body)
            this.enqueue(mockResponse)
        }
    }
}
