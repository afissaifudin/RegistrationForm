package com.afisdev.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
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
        viewDidLoad()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun navigate(action: NavDirections){
        findNavController().navigate(action)
    }

    fun navigateBack() {
        findNavController().navigateUp()
    }

    private fun setRegisterObserver() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            showSnackBar(message)
        }
    }

    fun showSnackBar(message: String) {
        binding.root.showSnackBar(message, 4000)
    }


}