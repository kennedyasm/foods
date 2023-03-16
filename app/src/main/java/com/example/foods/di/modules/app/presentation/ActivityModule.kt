package com.example.foods.di.modules.app.presentation

import com.example.foods.presentation.MainActivity
import com.example.foods.di.MainActivityScoped
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
interface ActivityModule {

    @MainActivityScoped
    @ContributesAndroidInjector(
        modules = [ViewModelBindsModule::class, MainActivityFragmentModules::class]
    )
    fun mainActivity(): MainActivity
}
