package com.afisdev.registrationform.feature.personaldata

import androidx.fragment.app.activityViewModels
import com.afisdev.common.customview.CustomInput
import com.afisdev.common.ui.BaseFragment
import com.afisdev.registrationform.R
import com.afisdev.registrationform.databinding.FragmentPersonalDataBinding
import com.afisdev.registrationform.feature.SharedViewModel
import com.afisdev.registrationform.utils.Education
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by afisdev on 08/09/2023.
 */
@AndroidEntryPoint
class PersonalDataFragment : BaseFragment<FragmentPersonalDataBinding, SharedViewModel>() {


    override val viewModel: SharedViewModel by activityViewModels()
    override val layoutResource = R.layout.fragment_personal_data
    private var customFields = ArrayList<CustomInput>()

    override fun viewDidLoad() {
        binding.fragment = this
        setupView()
        setupData()
    }

    override fun onBackEvent() {
        viewModel.resetData()
        super.onBackEvent()
    }

    private fun setupData() = with(binding) {
        viewModel.personalDataEntity.value?.let {
            ciNationalId.setText(it.nationalId)
            ciFullname.setText(it.fullName)
            ciBankAccount.setText(it.bankAccount)
            ciEducation.setText(it.education)
            ciDob.setText(it.dateOfBirth)
        }
    }

    private fun setupView() = with(binding) {
        customFields = arrayListOf(ciNationalId, ciFullname, ciBankAccount, ciEducation, ciDob)
        val educationOptions = Education.values().map { it.toString() }
        ciEducation.listInputValue = educationOptions
    }

    fun nextPage() {
        validationData(customFields) {
            val personalDataEntity = PersonalDataEntity(
                nationalId = binding.ciNationalId.getText(),
                fullName = binding.ciFullname.getText(),
                bankAccount = binding.ciBankAccount.getText(),
                education = binding.ciEducation.getText(),
                dateOfBirth = binding.ciDob.getText()
            )
            viewModel.setValuePersonalData(personalDataEntity)
            navigate(PersonalDataFragmentDirections.actionPersonalDataFragmentToResidentialDataFragment())
        }
    }
}