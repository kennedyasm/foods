package com.example.data.di

import com.example.common.async.rx.RxSchedulers
import com.example.common.async.rx.RxSchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SchedulersModule::class]
)
object FakeSchedulersModule {

    @Singleton
    @Provides
    fun providesRxSchedulers(): RxSchedulers = FakeRxSchedulers()
}

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {

    @Provides
    @Singleton
    fun providesRxSchedulers(): RxSchedulers = RxSchedulersImpl()
}
