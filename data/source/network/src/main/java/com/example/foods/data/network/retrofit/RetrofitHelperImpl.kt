package com.example.foods.data.network.retrofit

import com.example.foods.data.network.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelperImpl constructor(
    private val httpUrl: HttpUrl,
    interceptors: List<Interceptor>
) : RetrofitHelper {

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .apply { if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor) }
        .apply { interceptors.forEach(::addInterceptor) }
        .build()

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    companion object {
        private const val TIME_OUT: Long = 30
    }
}
