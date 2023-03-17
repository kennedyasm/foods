package com.example.foods.di.modules.app.presentation

import com.example.foods.di.MainActivityScoped
import com.example.foods.di.modules.ViewModelFactoryModule
import com.example.foods.presentation.MainActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
interface ActivityModule {

    @MainActivityScoped
    @ContributesAndroidInjector(
        modules = [ViewModelFactoryModule::class, ViewModelBindsModule::class, MainActivityFragmentModules::class]
    )
    fun mainActivity(): MainActivity
}
