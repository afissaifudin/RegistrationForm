package com.afisdev.common.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by afisdev on 08/09/2023.
 */
open class BaseViewModel: ViewModel() {

    var message = MutableLiveData<String>()

}