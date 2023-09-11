package com.afisdev.common.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity


/**
 * Created by afisdev on 08/09/2023.
 */

fun FragmentActivity?.hideKeyboard() {
    this?.currentFocus?.let { view ->
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}