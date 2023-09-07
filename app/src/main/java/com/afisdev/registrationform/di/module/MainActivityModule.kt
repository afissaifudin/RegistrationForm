package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by afisdev on 08/09/2023.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}