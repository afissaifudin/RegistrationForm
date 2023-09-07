package com.afisdev.registrationform.di.module

import androidx.lifecycle.ViewModelProvider
import com.afisdev.common.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module


/**
 * Created by afisdev on 08/09/2023.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}