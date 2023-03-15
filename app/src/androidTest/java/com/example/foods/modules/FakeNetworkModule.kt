package com.example.foods.modules

import com.example.foods.BuildConfig
import com.example.foods.common.Mocks
import com.example.foods.core.network.auth.AuthInterceptor
import com.example.foods.core.network.retrofit.RetrofitHelper
import com.example.foods.core.network.retrofit.RetrofitHelperImpl
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import javax.inject.Named

@Module
object FakeNetworkModule {

    @Named("base_http_url")
    @Provides
    fun provideBaseHttpUrl(): HttpUrl = Mocks.mockWebServer.url(BuildConfig.SERVICES_PATH)

    @Provides
    fun provideRetrofitHelper(
        @Named("base_http_url") baseHttpUrl: HttpUrl,
        @Named("auth_interceptor") authInterceptor: Interceptor
    ): RetrofitHelper {
        return RetrofitHelperImpl(baseHttpUrl, listOf(authInterceptor))
    }

    @Named("auth_interceptor")
    @Provides
    fun provideAuthInterceptor(): Interceptor = AuthInterceptor()
}
