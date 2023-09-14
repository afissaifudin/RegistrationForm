package com.afisdev.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.afisdev.common.R
import com.afisdev.common.customview.CustomInput
import com.afisdev.common.extension.showSnackBar


/**
 * Created by afisdev on 08/09/2023.
 */
abstract class BaseFragment<DB: ViewDataBinding, VM: BaseViewModel>: Fragment() {

    private lateinit var mContext: Context
    lateinit var binding: DB
    abstract val viewModel: VM


    abstract val layoutResource: Int

    abstract fun viewDidLoad()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRegisterObserver()
        setBackPressed()
        viewDidLoad()
    }

    private fun setBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackEvent()
                }
            }
        )
    }

    open fun onBackEvent() {
        val isNavigateUp = try { this.findNavController().navigateUp() } catch (e: Exception) { false }
        if (!isNavigateUp) {
            if (parentFragment is NavHostFragment && parentFragment?.parentFragment?.findNavController() != null) {
                parentFragment?.parentFragment?.findNavController()?.navigateUp()
            } else {
                activity?.finish()
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected fun navigate(action: NavDirections){
        findNavController().navigate(action)
    }

    fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun setRegisterObserver() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            showSnackBar(message)
        }
    }

    protected fun dataValidation(customFields: ArrayList<CustomInput>, actionValid: ()-> Unit) {
        val fieldsError = ArrayList<String?>()
        customFields.forEach{ customInput ->
            fieldsError.add(customInput.checkAndGetFieldError())
        }
        val fieldsErrorNonNull = fieldsError.filterNotNull()
        val resutlFields = fieldsErrorNonNull.joinToString(", ")
        if (fieldsErrorNonNull.isNotEmpty()) {
            showSnackBar(getString(R.string.msg_error_check_field, resutlFields))
        } else {
            actionValid()
        }
    }

    protected fun isFieldFilled(customFields: ArrayList<CustomInput>): Boolean {
        customFields.forEach{ customInput ->
            if (customInput.getText().isNotEmpty()) return true
        }
        return false
    }

    private fun showSnackBar(message: String) {
        binding.root.showSnackBar(message, 4000)
    }
}