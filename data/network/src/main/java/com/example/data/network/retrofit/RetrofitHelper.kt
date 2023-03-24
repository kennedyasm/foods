package com.example.data.network.retrofit

interface RetrofitHelper {
    fun <T> createService(clazz: Class<T>): T
}
