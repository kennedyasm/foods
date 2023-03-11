package com.example.foods.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.foods.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
