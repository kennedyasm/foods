package com.example.foods.di.modules.app.presentation

import com.example.foods.presentation.MainActivity
import com.example.foods.di.ActivityScoped
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [ViewModelBindsModule::class, MainActivityFragmentModules::class]
    )
    fun mainActivity(): MainActivity
}
