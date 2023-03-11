package com.example.foods.di.modules

import com.example.foods.core.rx.RxSchedulers
import com.example.foods.core.rx.RxSchedulersImpl
import dagger.Module
import dagger.Provides

@Module
object RxSchedulersModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers = RxSchedulersImpl()
}
