package com.afisdev.common.customview

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.afisdev.common.R
import com.afisdev.common.databinding.CompCustomEdittextBinding
import com.afisdev.common.extension.afterTextChanged


/**
 * Created by afisdev on 08/09/2023.
 */
class CustomEditText constructor(
    context: Context,
    attributeSet: AttributeSet
): ConstraintLayout(context, attributeSet) {

    lateinit var binding: CompCustomEdittextBinding

    var requiredInput: Boolean? = false
    var minimumInputValue: Int = 0
    var maximumInputValue: Int? = 0
    var errorInputValue: String? = null
    var typeInputValue: Int = 0

    init {
        setupProperty(context, attributeSet)
    }

    @SuppressLint("Recycle")
    private fun setupProperty(context: Context, attributeSet: AttributeSet) {
        binding = CompCustomEdittextBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomEditText)
        val titleInputValue = attributes.getString(R.styleable.CustomEditText_titleInput)
        val hintInputValue = attributes.getString(R.styleable.CustomEditText_hintInput)
        typeInputValue = attributes.getInt(R.styleable.CustomEditText_typeInput, InputType.TYPE_CLASS_TEXT)
        errorInputValue = attributes.getString(R.styleable.CustomEditText_errorInput)
        requiredInput = attributes.getBoolean(R.styleable.CustomEditText_requiredInput, false)
        maximumInputValue = attributes.getInt(R.styleable.CustomEditText_maximumInput, 100)
        minimumInputValue = attributes.getInt(R.styleable.CustomEditText_minimumInput, 0)

        binding.tvLabelInput.text = titleInputValue
        binding.tieMainEdittext.apply {
            hint = hintInputValue
            inputType = typeInputValue
            maxLengthInput(maximumInputValue)
            minLengthInputWithError(minimumInputValue, errorInputValue, titleInputValue)
        }

        attributes.recycle()
    }

    private fun minLengthInputWithError(minInput: Int, errorMessage: String?, titleInput: String? = "") {

        binding.tieMainEdittext.apply {
            afterTextChanged {
                error = if (it.isEmpty()) {
                    titleInput + context.getString(R.string.msg_error_cant_be_empty)
                } else if (it.length < minInput) {
                    errorMessage?: context.getString(R.string.msg_error_min_char, minInput)
                } else {
                    null
                }
            }

//            this@apply.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
//                if (!hasFocus) {
//                    // EditText kehilangan fokus, Anda dapat menghitung jumlah karakter di sini
//                    val text = binding.tilMainLayout.editText.toString()
//                    val charCount = text.length
//                    error = if (text.isEmpty()) {
//                        titleInput + context.getString(R.string.msg_error_cant_be_empty)
//                    } else if (charCount < minInput) {
//                        errorMessage?: context.getString(R.string.msg_error_min_char, minInput)
//                    } else {
//                        null
//                    }
//                }
//            }
        }
    }

    private fun EditText.maxLengthInput(max: Int?) {
        val currentMax = max ?: 0
        this.filters = arrayOf(InputFilter.LengthFilter(currentMax))
    }
}