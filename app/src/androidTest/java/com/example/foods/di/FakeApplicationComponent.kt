package com.example.foods.di

import android.content.Context
import com.example.foods.di.modules.app.AppModule
import com.example.foods.di.modules.FakeDispatchersModule
import com.example.foods.di.modules.FakeNetworkModule
import com.example.foods.di.modules.FakeLocalModule
import com.example.foods.di.modules.FakeRxSchedulersModule
import com.example.foods.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        FakeRxSchedulersModule::class,
        FakeDispatchersModule::class,
        FakeNetworkModule::class,
        FakeLocalModule::class
    ]
)
interface FakeApplicationComponent: AppComponent {

    @Component.Factory
    interface Factory {
        fun create (@BindsInstance context: Context): AppComponent
    }
}
