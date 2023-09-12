package com.afisdev.registrationform.feature.welcome

import androidx.fragment.app.activityViewModels
import com.afisdev.common.ui.BaseFragment
import com.afisdev.registrationform.R
import com.afisdev.registrationform.databinding.FragmentWelcomeBinding
import com.afisdev.registrationform.feature.SharedViewModel

/**
 * Created by afisdev on 08/09/2023.
 */
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, SharedViewModel>() {

    override val viewModel: SharedViewModel by activityViewModels()
    override val layoutResource: Int = R.layout.fragment_welcome

    override fun viewDidLoad() {
        binding.fragment = this
    }

    fun openRegistration() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToPersonalDataFragment())
    }
}