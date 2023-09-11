package com.afisdev.registrationform.feature

import com.afisdev.common.ui.BaseViewModel
import com.afisdev.registrationform.domain.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by afisdev on 08/09/2023.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val useCase: MainUseCase,
): BaseViewModel() {
}