package com.example.foods.data.source.network.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foods.data.source.network.BuildConfig
import com.example.foods.data.source.network.auth.AuthInterceptor
import com.example.foods.data.source.network.retrofit.RetrofitHelper
import com.example.foods.data.source.network.retrofit.RetrofitHelperImpl
import okhttp3.Interceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule

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
}
