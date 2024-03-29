package com.example.foods.core.testing

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver

fun <T : Any> Single<T>.testAndGetData(): T =
    this.test().await().assertNoErrors().assertComplete().values().first()

fun <T : Any> Single<T>.completeAssertedTest(): TestObserver<T> =
    this.test().await().assertNoErrors().assertComplete()
