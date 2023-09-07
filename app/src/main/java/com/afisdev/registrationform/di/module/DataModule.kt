package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.data.remote.datasource.MainDataSource
import com.afisdev.registrationform.data.remote.datasource.MainDataSourceImpl
import com.afisdev.registrationform.data.repository.MainRepository
import com.afisdev.registrationform.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


/**
 * Created by afisdev on 08/09/2023.
 */
@Module
abstract class MainDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindMainDataSource(
        bindMainDataSource: MainDataSourceImpl
    ): MainDataSource
}

@Module
abstract class MainRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMainRepository(
        mainRepository: MainRepositoryImpl
    ): MainRepository
}