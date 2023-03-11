package com.example.foods.core.network.retrofit

interface RetrofitHelper {
    fun <T> createService(clazz: Class<T>): T
}
