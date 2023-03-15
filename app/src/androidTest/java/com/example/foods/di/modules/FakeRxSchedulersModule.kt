package com.example.foods.di.modules


import com.example.foods.core.rx.RxSchedulers
import com.example.foods.core.rx.FakeRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FakeRxSchedulersModule {

    @Singleton
    @Provides
    fun provideFakeRxScheduler(): RxSchedulers = FakeRxSchedulers()
}
