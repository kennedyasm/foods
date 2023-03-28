package com.example.data


import com.example.foods.core.testing.MySharedMockWebServer
import com.example.data.network.BuildConfig
import com.example.data.network.auth.AuthInterceptor
import com.example.data.network.di.NetworkModule
import com.example.data.network.retrofit.RetrofitHelper
import com.example.data.network.retrofit.RetrofitHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import okhttp3.Interceptor
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNetworkModule {

    @Named("base_http_url")
    @Provides
    fun provideBaseHttpUrl(): HttpUrl = MySharedMockWebServer.mockWebServer.url(BuildConfig.SERVICES_PATH)

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
