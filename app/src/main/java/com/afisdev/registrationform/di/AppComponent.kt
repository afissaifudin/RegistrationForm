package com.afisdev.registrationform.di

import android.app.Application
import com.afisdev.registrationform.FormApp
import com.afisdev.registrationform.di.module.AppModule
import com.afisdev.registrationform.di.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by afisdev on 08/09/2023.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<FormApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(formApp: FormApp)
}