package com.example.foods.data.source.network.retrofit

interface RetrofitHelper {
    fun <T> createService(clazz: Class<T>): T
}
