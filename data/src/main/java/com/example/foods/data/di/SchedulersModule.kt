package com.example.foods.data.di

import com.example.foods.data.scheduler.RxSchedulers
import com.example.foods.data.scheduler.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {

    @Provides
    @Singleton
    fun providesRxSchedulers(): RxSchedulers = RxSchedulersImpl()
}
