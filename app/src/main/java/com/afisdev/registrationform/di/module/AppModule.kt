package com.afisdev.registrationform.di.module

import dagger.Module


/**
 * Created by afisdev on 08/09/2023.
 */
@Module(
    includes = [
        NetworkModule::class,
        MainDataSourceModule::class,
        MainRepositoryModule::class,
        ViewModelModule::class
    ]
)
class AppModule