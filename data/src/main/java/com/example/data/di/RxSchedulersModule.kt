package com.example.data.di

import com.example.data.rx.RxSchedulers
import com.example.data.rx.RxSchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RxSchedulersModule {

    @Binds
    fun bindsRxSchedulers(
        impl: RxSchedulersImpl
    ): RxSchedulers
}