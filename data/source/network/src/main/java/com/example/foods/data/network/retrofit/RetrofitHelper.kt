package com.example.foods.data.network.retrofit

interface RetrofitHelper {
    fun <T> createService(clazz: Class<T>): T
}
