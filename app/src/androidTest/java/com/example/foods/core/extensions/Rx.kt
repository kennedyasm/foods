package com.example.foods.core.extensions

import io.reactivex.rxjava3.core.Single

internal fun <T : Any> Single<T>.testAndGetData() =
    this.test().await().assertNoErrors().assertComplete().values().first()
