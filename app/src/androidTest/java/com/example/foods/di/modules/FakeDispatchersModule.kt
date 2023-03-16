package com.example.foods.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Module
object FakeDispatchersModule {

    @Singleton
    @Provides
    fun provideDispatcherDefault(): CoroutineDispatcher = UnconfinedTestDispatcher()
}