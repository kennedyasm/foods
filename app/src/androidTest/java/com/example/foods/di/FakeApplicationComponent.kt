package com.example.foods.di

import android.content.Context
import com.example.foods.di.AppComponent
import com.example.foods.di.modules.*
import com.example.foods.di.modules.app.AppModule
import com.example.foods.modules.FakeDispatchersModule
import com.example.foods.modules.FakeNetworkModule
import com.example.foods.modules.FakeLocalModule
import com.example.foods.modules.FakeRxSchedulersModule
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
