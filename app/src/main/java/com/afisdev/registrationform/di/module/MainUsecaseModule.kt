package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.data.repository.MainRepository
import com.afisdev.registrationform.domain.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainUsecaseModule {

    @Provides
    fun provideMainUsecase(mainRepository: MainRepository): MainUseCase = MainUseCase(mainRepository)
}