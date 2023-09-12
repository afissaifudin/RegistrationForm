package com.afisdev.common.customview

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
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
import com.afisdev.common.databinding.CompCustomInputBinding
import com.afisdev.common.extension.hideKeyboard
import com.afisdev.common.util.Constant
import java.text.SimpleDateFormat
import java.util.Calendar


/**
 * Created by afisdev on 08/09/2023.
 */
class CustomInput constructor(
    context: Context,
    attributeSet: AttributeSet
): ConstraintLayout(context, attributeSet) {

    private lateinit var binding: CompCustomInputBinding

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
        binding = CompCustomInputBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomInput)
        titleInputValue = attributes.getString(R.styleable.CustomInput_titleInput)
        hintInputValue = attributes.getString(R.styleable.CustomInput_hintInput)
        typeInputValue = attributes.getInt(R.styleable.CustomInput_typeInput, InputType.TYPE_CLASS_TEXT)
        errorInputValue = attributes.getString(R.styleable.CustomInput_errorInput)
        maximumInputValue = attributes.getInt(R.styleable.CustomInput_maximumInput, 100)
        minimumInputValue = attributes.getInt(R.styleable.CustomInput_minimumInput, 0)

        binding.tvLabelInput.text = titleInputValue
        setupView()
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
       if (typeInputValue == DROPDOWN_TYPE ) {
           tieMainEdittext.isVisible =  false
           tilDropdown.isVisible = true
           atvDropdown.hint = hintInputValue
           atvDropdown.setOnItemClickListener { _, _, _, _ ->
               atvDropdown.error = null
           }
       } else {
           tieMainEdittext.apply {
               hint = hintInputValue
               inputType = getTypeInputValue()
               maxLengthInput(maximumInputValue)
               minLengthInputWithError(minimumInputValue, errorInputValue, titleInputValue)
               setupViewInputValue(typeInputValue)
           }
       }
    }

    private fun getTypeInputValue(): Int {
        return when(typeInputValue) {
            TEXT_TYPE, CHARONLY_TYPE -> InputType.TYPE_CLASS_TEXT
            NUMBER_TYPE -> InputType.TYPE_CLASS_NUMBER
            else -> InputType.TYPE_NULL
        }
    }

    private fun setupViewInputValue(typeInputValue: Int) {
        // change view when view is datepicker / dropdown
        if (typeInputValue == DATEPICKER_TYPE) {
            binding.tilMainLayout.setOnKeyListener(null)
            binding.tieMainEdittext.apply {
                isFocusableInTouchMode = false
                isFocusable = false
                setOnClickListener {
                    this@CustomInput.apply {
                        hideKeyboard()
                        error = null
                    }
                    chooseDate()
                }
            }
        } else if (typeInputValue == CHARONLY_TYPE) {
            binding.tieMainEdittext.keyListener = DigitsKeyListener.getInstance(context.getString(R.string.accepted_char))
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
        datePicker.datePicker.maxDate = calendar.timeInMillis
        datePicker.show()
    }

    private fun minLengthInputWithError(minInput: Int, errorMessage: String?, titleInput: String? = "") {

        binding.tieMainEdittext.apply {
            this@apply.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val text = binding.tilMainLayout.editText?.text.toString()
                    error = if (text.isEmpty()) {
                        context.getString(R.string.msg_error_cant_be_empty, titleInput)
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

    fun checkAndGetFieldError(): String? {
        with(binding) {
            if (typeInputValue == DROPDOWN_TYPE) {
                val valueDropDown = atvDropdown.text.toString()
                atvDropdown.error = if (valueDropDown.isEmpty()) {
                    context.getString(R.string.msg_error_cant_be_empty,titleInputValue)
                } else null
                return if (atvDropdown.error != null) titleInputValue else null
            } else {
                val valueEditText = tilMainLayout.editText?.text.toString()
                tieMainEdittext.error = if (valueEditText.isEmpty()) {
                    context.getString(R.string.msg_error_cant_be_empty, titleInputValue)
                } else if (valueEditText.length < minimumInputValue) {
                    errorInputValue?: context.getString(R.string.msg_error_min_char, minimumInputValue)
                } else null
                return if (tieMainEdittext.error != null) titleInputValue else null
            }
        }
    }

    fun getText(): String {
        with(binding) {
            return if (typeInputValue == DROPDOWN_TYPE) {
                tilDropdown.editText?.text.toString()
            } else {
                tilMainLayout.editText?.text.toString()
            }
        }

    }

    companion object {
        const val TEXT_TYPE = 1
        const val NUMBER_TYPE = 2
        const val CHARONLY_TYPE = 3
        const val DATEPICKER_TYPE = 4
        const val DROPDOWN_TYPE = 5
    }
}