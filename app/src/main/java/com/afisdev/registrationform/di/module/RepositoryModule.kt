package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.data.remote.datasource.MainDataSource
import com.afisdev.registrationform.data.repository.MainRepository
import com.afisdev.registrationform.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepository(mainDataSource: MainDataSource): MainRepository = MainRepositoryImpl(mainDataSource)
}