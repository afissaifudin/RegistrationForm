package com.afisdev.registrationform.di.module

import com.afisdev.registrationform.feature.personaldata.PersonalDataFragment
import com.afisdev.registrationform.feature.preview.PreviewFragment
import com.afisdev.registrationform.feature.residentialdata.ResidentialDataFragment
import com.afisdev.registrationform.feature.welcome.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by afisdev on 08/09/2023.
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesWelcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector
    abstract fun contributePersonalDataFragment(): PersonalDataFragment

    @ContributesAndroidInjector
    abstract fun contributesResidentialDataFragment(): ResidentialDataFragment

    @ContributesAndroidInjector
    abstract fun contributesPreviewFragment(): PreviewFragment
}