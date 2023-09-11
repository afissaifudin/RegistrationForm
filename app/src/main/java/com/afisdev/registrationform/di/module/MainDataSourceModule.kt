package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.data.remote.api.ApiService
import com.afisdev.registrationform.data.remote.datasource.MainDataSource
import com.afisdev.registrationform.data.remote.datasource.MainDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainDataSourceModule {

    @Provides
    fun provideMainDataSource(apiService: ApiService): MainDataSource = MainDataSourceImpl(apiService)
}