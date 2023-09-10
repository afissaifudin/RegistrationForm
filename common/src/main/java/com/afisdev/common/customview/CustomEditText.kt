package com.afisdev.common.customview

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.afisdev.common.R
import com.afisdev.common.databinding.CompCustomEdittextBinding
import com.afisdev.common.extension.hideKeyboard
import com.afisdev.common.util.Constant
import java.text.SimpleDateFormat
import java.util.Calendar


/**
 * Created by afisdev on 08/09/2023.
 */
class CustomEditText constructor(
    context: Context,
    attributeSet: AttributeSet
): ConstraintLayout(context, attributeSet) {

    private lateinit var binding: CompCustomEdittextBinding

    private var titleInputValue: String? = ""
    private var hintInputValue: String? = ""
    private var minimumInputValue: Int = 0
    private var maximumInputValue: Int? = 0
    private var errorInputValue: String? = null
    private var typeInputValue: Int = 0

    var listInputValue: List<String> = arrayListOf()
        set(value) {
            field = value
            setAdapterDropdown()
        }

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
        titleInputValue = attributes.getString(R.styleable.CustomEditText_titleInput)
        hintInputValue = attributes.getString(R.styleable.CustomEditText_hintInput)
        typeInputValue = attributes.getInt(R.styleable.CustomEditText_typeInput, InputType.TYPE_CLASS_TEXT)
        errorInputValue = attributes.getString(R.styleable.CustomEditText_errorInput)
        maximumInputValue = attributes.getInt(R.styleable.CustomEditText_maximumInput, 100)
        minimumInputValue = attributes.getInt(R.styleable.CustomEditText_minimumInput, 0)

        binding.tvLabelInput.text = titleInputValue
        setupView()
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
       if (typeInputValue == DROPDOWNTYPE ) {
           tieMainEdittext.isVisible =  false
           tilDropdown.isVisible = true
           atvDropdown.hint = hintInputValue
       } else {
           tieMainEdittext.apply {
               hint = hintInputValue
               inputType = typeInputValue
               maxLengthInput(maximumInputValue)
               minLengthInputWithError(minimumInputValue, errorInputValue, titleInputValue)
               setupViewInputValue(typeInputValue)
           }
       }
    }

    private fun setupViewInputValue(typeInputValue: Int) {
        // change view when view is datepicker / dropdown
        if (typeInputValue == DATEPICKERTYPE) {
            binding.tilMainLayout.setOnKeyListener(null)
            binding.tieMainEdittext.apply {
                isFocusableInTouchMode = false
                isFocusable = false
                setOnClickListener {
                    this@CustomEditText.hideKeyboard()
                    chooseDate()
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun chooseDate() {
        val etDatePicker = binding.tilMainLayout
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat(Constant.date_format)
        val date = try {
            formatter.parse(etDatePicker.editText?.text.toString())
        } catch (ignored: Exception) {
            Log.e("Datepicker","formatter $ignored")
            null
        }
        date?.let {
            calendar.time = it
        }

        val yy = calendar[Calendar.YEAR]
        val mm = calendar[Calendar.MONTH]
        val dd = calendar[Calendar.DAY_OF_MONTH]
        val datePicker = DatePickerDialog(
            context,
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar[year, monthOfYear] = dayOfMonth
                val strDate = formatter.format(calendar.time)
                etDatePicker.editText?.setText(strDate)
            },
            yy, mm, dd
        )
        datePicker.show()
    }

    private fun minLengthInputWithError(minInput: Int, errorMessage: String?, titleInput: String? = "") {

        binding.tieMainEdittext.apply {
            this@apply.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val text = binding.tilMainLayout.editText?.text.toString()
                    error = if (text.isEmpty()) {
                        titleInput + context.getString(R.string.msg_error_cant_be_empty)
                    } else if (text.length < minInput) {
                        errorMessage?: context.getString(R.string.msg_error_min_char, minInput)
                    } else {
                        null
                    }
                }
            }
        }
    }

    private fun setAdapterDropdown() {
        val listDropdown = listInputValue.toTypedArray()
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, listDropdown)
        binding.atvDropdown.setAdapter(adapter)
    }

    private fun EditText.maxLengthInput(max: Int?) {
        val currentMax = max ?: 0
        this.filters = arrayOf(InputFilter.LengthFilter(currentMax))
    }

    private fun checkField() = with(binding) {
        if (typeInputValue == DROPDOWNTYPE) {
            val valueDropDown = atvDropdown.text.toString()
            atvDropdown.error = if (valueDropDown.isEmpty()) {
                titleInputValue + context.getString(R.string.msg_error_cant_be_empty)
            } else null
        } else {
            val text = tilMainLayout.editText?.text.toString()
            tieMainEdittext.error = if (text.isEmpty()) {
                titleInputValue + context.getString(R.string.msg_error_cant_be_empty)
            } else if (text.length < minimumInputValue) {
                errorInputValue?: context.getString(R.string.msg_error_min_char, minimumInputValue)
            } else {
                null
            }
        }

    }

    fun isCustomEditTextError(): Boolean {
        checkField()
        return if (typeInputValue == DROPDOWNTYPE) {
            binding.atvDropdown.text.toString().isEmpty()
        } else {
            binding.tieMainEdittext.error != null
        }
    }

    companion object {
        const val DATEPICKERTYPE = 0
        const val DROPDOWNTYPE = 3
    }
}