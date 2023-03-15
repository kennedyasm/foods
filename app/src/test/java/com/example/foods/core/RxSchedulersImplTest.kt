package com.example.foods.core

import com.example.foods.core.rx.RxSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class RxSchedulersImplTest: RxSchedulers {
    override val io: Scheduler
        get() = Schedulers.trampoline()
    override val mainThread: Scheduler
        get() = Schedulers.trampoline()
}