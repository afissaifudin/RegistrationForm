package com.afisdev.common.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider


/**
 * Created by afisdev on 08/09/2023.
 */
inline fun <reified T : ViewModel> Fragment.viewModelProvider(viewModelProvider: Provider<T>) =
    ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            viewModelProvider.get() as T
    })[T::class.java]

fun FragmentActivity?.hideKeyboard() {
    this?.currentFocus?.let { view ->
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}