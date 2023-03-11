package com.example.foods.di.modules.app

import com.example.foods.di.modules.app.presentation.ActivityBindingModule
import com.example.foods.di.modules.app.ApiServicesModule
import com.example.foods.di.modules.app.DataSourceModule
import com.example.foods.di.modules.app.RepositoryModule
import com.example.foods.di.modules.app.UseCasesModule
import dagger.Module

@Module(
    includes = [
        ActivityBindingModule::class,
        UseCasesModule::class,
        RepositoryModule::class,
        DataSourceModule::class,
        ApiServicesModule::class
    ]
)
open class AppModule
