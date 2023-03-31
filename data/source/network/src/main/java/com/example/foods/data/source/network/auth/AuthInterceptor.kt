package com.example.foods.data.source.network.auth

import com.example.foods.data.source.network.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private fun addSecurityQueryParametersInUrlRequest(httpUrl: HttpUrl): HttpUrl {
        return httpUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlRequest = addSecurityQueryParametersInUrlRequest(request.url)
        val newRequest = request.newBuilder().url(urlRequest).build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val API_KEY = "api_key"
        private const val apiKey = BuildConfig.API_KEY
    }
}
