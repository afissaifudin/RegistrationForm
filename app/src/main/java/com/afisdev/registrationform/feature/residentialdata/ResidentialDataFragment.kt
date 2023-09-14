package com.afisdev.registrationform.feature.residentialdata

import androidx.fragment.app.activityViewModels
import com.afisdev.common.customview.CustomInput
import com.afisdev.common.ui.BaseFragment
import com.afisdev.registrationform.R
import com.afisdev.registrationform.databinding.FragmentResidentialDataBinding
import com.afisdev.registrationform.feature.SharedViewModel
import com.afisdev.registrationform.utils.HousingType
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by afisdev on 08/09/2023.
 */
@AndroidEntryPoint
class ResidentialDataFragment : BaseFragment<FragmentResidentialDataBinding, SharedViewModel>() {

    override val viewModel: SharedViewModel by activityViewModels()
    override val layoutResource = R.layout.fragment_residential_data
    private var customFields = ArrayList<CustomInput>()

    override fun viewDidLoad() {
        binding.fragment = this
        setupView()
        setupData()
    }

    override fun onBackEvent() {
        saveStateData()
        super.onBackEvent()
    }

    private fun setupView() = with(binding) {
        val housingTypeOptions = HousingType.values().map { it.toString() }
        binding.ciHousingType.listInputValue = housingTypeOptions

        val provinceOption = viewModel.provinceList.value
        binding.ciProvince.listInputValue = provinceOption.orEmpty()

        customFields = arrayListOf(ciDomicilieAddress, ciHousingType, ciHousingNumber, ciProvince)
    }

    private fun setupData() = with(binding) {
        viewModel.residentialDataEntity.value?.let {
            ciDomicilieAddress.setText(it.domicile)
            ciHousingType.setText(it.housingType)
            ciHousingNumber.setText(it.housingNumber)
            ciProvince.setText(it.province)
        }
    }

    private fun saveStateData() = with(binding) {
        val residentialDataEntity = ResidentialDataEntity(
            domicile = ciDomicilieAddress.getText(),
            housingType = ciHousingType.getText(),
            housingNumber = ciHousingNumber.getText(),
            province = ciProvince.getText()
        )
        viewModel.setValueResidentialData(residentialDataEntity)
    }

    fun nextPage() {
        dataValidation(customFields) {
            saveStateData()
            navigate(ResidentialDataFragmentDirections.actionResidentialDataFragmentToPreviewFragment())
        }
    }
}