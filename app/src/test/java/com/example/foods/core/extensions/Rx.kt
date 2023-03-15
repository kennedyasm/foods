package com.example.foods.core.extensions

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver


internal fun <T : Any> Single<T>.testAndGetData() =
    this.test().await().assertNoErrors().assertComplete().values().first()

internal fun <T : Any> Single<T>.completeAssertedTest(): TestObserver<T> =
    this.test().await().assertNoErrors().assertComplete()