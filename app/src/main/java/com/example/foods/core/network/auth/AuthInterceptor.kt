package com.example.foods.core.network.auth

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private fun addSecurityHeadersInChainRequest(request: Request): Request {
        return request.newBuilder()
            .addHeader(AUTHORIZATION, accessToken)
            .build()
    }

    private fun addSecurityQueryParametersInUrlRequest(httpUrl: HttpUrl): HttpUrl {
        return httpUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithHeaders = addSecurityHeadersInChainRequest(chain.request())
        val urlRequest = addSecurityQueryParametersInUrlRequest(requestWithHeaders.url)
        val request = requestWithHeaders.newBuilder().url(urlRequest).build()
        return chain.proceed(request)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
        private const val accessToken = "BearerTokenValue"//"$BEARER ${BuildConfig.ACCESS_TOKEN}"
        private const val API_KEY = "api_key"
        private const val apiKey = "ApiKeyValue"//BuildConfig.API_KEY
    }
}
