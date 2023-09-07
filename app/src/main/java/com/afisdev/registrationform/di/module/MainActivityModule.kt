package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by afisdev on 08/09/2023.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}