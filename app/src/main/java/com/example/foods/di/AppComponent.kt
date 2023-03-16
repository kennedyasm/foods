package com.example.foods.di

import android.content.Context
import com.example.foods.App
import com.example.foods.di.modules.DispatchersModule
import com.example.foods.di.modules.LocalModule
import com.example.foods.di.modules.NetworkModule
import com.example.foods.di.modules.RxSchedulersModule
import com.example.foods.di.modules.ViewModelFactoryModule
import com.example.foods.di.modules.app.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

interface AppComponent : AndroidInjector<App>

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        RxSchedulersModule::class,
        DispatchersModule::class,
        NetworkModule::class,
        LocalModule::class
    ]
)
interface ApplicationComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
