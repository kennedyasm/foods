package com.example.data.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulersImpl @Inject constructor(): RxSchedulers {
    override val io: Scheduler
        get() = Schedulers.io()

    override val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()
}
