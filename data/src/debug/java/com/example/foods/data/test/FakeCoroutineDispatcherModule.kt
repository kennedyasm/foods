package com.example.foods.data.test

import com.example.foods.data.di.CoroutineDispatcherModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineDispatcherModule::class]
)
object FakeCoroutineDispatcherModule {

    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher = UnconfinedTestDispatcher()
}