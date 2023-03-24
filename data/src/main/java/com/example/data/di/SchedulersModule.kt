package com.example.data.di

import com.example.common.async.rx.RxSchedulers
import com.example.common.async.rx.RxSchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SchedulersModule {

    @Binds
    fun bindsRxSchedulers(impl: RxSchedulersImpl): RxSchedulers
}
