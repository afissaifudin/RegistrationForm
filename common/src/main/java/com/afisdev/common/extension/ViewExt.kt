package com.afisdev.common.extension

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import com.afisdev.common.R
import com.afisdev.common.customview.CustomInput
import com.google.android.material.snackbar.Snackbar


/**
 * Created by afisdev on 08/09/2023.
 */

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

@BindingAdapter("textInputValue")
fun CustomInput.setTextInputValue(value: String?) {
    setText(value)
}

fun View.showConfirmationDialog(
    title: String,
    message: String,
    labelYes: String = context.getString(R.string.label_yes),
    labelNo: String = context.getString(R.string.label_no),
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {}
) {
    AlertDialog.Builder(this.context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(labelYes) { _, _ ->
            onConfirm()
        }
        .setNegativeButton(labelNo) { dialog, _ ->
            dialog.dismiss()
            onCancel()
        }
        .create()
        .show()
}