package com.example.foods.data.network.di

import com.example.foods.data.network.BuildConfig
import com.example.foods.data.network.auth.AuthInterceptor
import com.example.foods.data.network.retrofit.RetrofitHelper
import com.example.foods.data.network.retrofit.RetrofitHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val url = BuildConfig.BASE_URL + BuildConfig.SERVICES_PATH

    @Named("base_http_url")
    @Provides
    fun provideBaseHttpUrl(): HttpUrl = (url).toHttpUrl()

    @Provides
    fun provideRetrofitHelper(
        @Named("base_http_url") baseHttpUrl: HttpUrl,
        @Named("auth_interceptor") authInterceptor: Interceptor
    ): RetrofitHelper = RetrofitHelperImpl(baseHttpUrl, listOf(authInterceptor))

    @Named("auth_interceptor")
    @Provides
    fun provideAuthInterceptor(): Interceptor = AuthInterceptor()
}
