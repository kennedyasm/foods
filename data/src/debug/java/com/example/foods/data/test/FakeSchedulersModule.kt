package com.example.foods.data.test

import com.example.foods.data.di.SchedulersModule
import com.example.foods.data.scheduler.RxSchedulers
import dagger.Module
import dagger.Provides
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
