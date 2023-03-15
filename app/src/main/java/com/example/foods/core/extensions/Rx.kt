package com.example.foods.core.extensions

import com.example.foods.core.rx.RxSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

fun <T : Any> Single<T>.runIo(rxSchedulers: RxSchedulers): Single<T> {
    return this.subscribeOn(rxSchedulers.io).observeOn(rxSchedulers.mainThread)
}
