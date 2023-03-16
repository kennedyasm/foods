package com.example.foods.di.modules.app

import com.example.foods.di.modules.app.presentation.ActivityModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        UseCasesModule::class,
        RepositoryModule::class,
        DataSourceModule::class,
        ApiServicesModule::class
    ]
)
open class AppModule
