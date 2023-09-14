package com.afisdev.registrationform.feature.registrationsuccess

import androidx.fragment.app.activityViewModels
import com.afisdev.common.ui.BaseFragment
import com.afisdev.registrationform.R
import com.afisdev.registrationform.databinding.FragmentRegistrationSuccessBinding
import com.afisdev.registrationform.feature.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by afisdev on 14/09/2023.
 */
@AndroidEntryPoint
class RegistrationSuccessFragment : BaseFragment<FragmentRegistrationSuccessBinding, SharedViewModel>() {

    override val viewModel: SharedViewModel by activityViewModels()
    override val layoutResource = R.layout.fragment_registration_success

    override fun viewDidLoad() {
        binding.fragment = this
        binding.btnOkay.setOnClickListener {
        }
    }

    fun backToWelcome() {
        navigate(
            RegistrationSuccessFragmentDirections.actionRegistrationSuccessFragmentToWelcomeFragment()
        )
    }
}