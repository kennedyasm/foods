package com.example.foods.data.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface RxSchedulers {
    val io: Scheduler
    val mainThread: Scheduler
}
