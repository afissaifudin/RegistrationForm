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
    }

    private fun setupView() = with(binding) {
        val educationOptions = Education.values().map { it.toString() }
        ciEducation.listInputValue = educationOptions

        customFields = arrayListOf(ciNationalId, ciFullname, ciBankAccount, ciEducation, ciDob)

        ivBack.setOnClickListener{
            navigateBack()
        }
    }

    fun nextPage() {
        val fieldsError = ArrayList<String?>()
        customFields.forEach{ customInput ->
            fieldsError.add(customInput.checkAndGetFieldError())
        }
        val fieldErrorNonNull = fieldsError.filterNotNull()
        val resutlFields = fieldErrorNonNull.joinToString(", ")
        if (fieldErrorNonNull.isNotEmpty()) {
            showSnackBar(getString(R.string.msg_error_check_field, resutlFields))
        } else {
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