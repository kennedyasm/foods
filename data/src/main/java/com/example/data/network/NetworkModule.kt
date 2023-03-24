package com.example.data.network

import com.example.data.BuildConfig
import com.example.data.local.datasource.FoodRecipesLocalDataSource
import com.example.data.network.auth.AuthInterceptor
import com.example.data.network.datasource.FoodRecipesNetworkDataSource
import com.example.data.network.retrofit.RetrofitHelper
import com.example.data.network.retrofit.RetrofitHelperImpl
import com.example.data.repository.FoodRecipesRepositoryImpl
import com.example.data.rx.RxSchedulers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun providesRepository(
        localDataSource: FoodRecipesLocalDataSource,
        networkDataSource: FoodRecipesNetworkDataSource,
        schedulers: RxSchedulers,
        dispatcherIO: CoroutineDispatcher
    ) = FoodRecipesRepositoryImpl(networkDataSource, localDataSource, schedulers, dispatcherIO)
}
