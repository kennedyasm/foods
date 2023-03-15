package com.example.foods.di.modules

import com.example.foods.core.rx.RxSchedulers
import com.example.foods.core.rx.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RxSchedulersModule {

    @Singleton
    @Provides
    fun provideRxSchedulers(): RxSchedulers = RxSchedulersImpl()
}
