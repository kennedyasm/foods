package com.example.common.testing

import com.example.common.async.rx.RxSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class RxSchedulersImplTest: RxSchedulers {
    override val io: Scheduler
        get() = Schedulers.trampoline()
    override val mainThread: Scheduler
        get() = Schedulers.trampoline()
}
