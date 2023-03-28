package com.example.core.async.rx

import io.reactivex.rxjava3.core.Scheduler

interface RxSchedulers {
    val io: Scheduler
    val mainThread: Scheduler
}
