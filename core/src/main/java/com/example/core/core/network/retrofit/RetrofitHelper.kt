package com.example.core.core.network.retrofit

interface RetrofitHelper {
    fun <T> createService(clazz: Class<T>): T
}
