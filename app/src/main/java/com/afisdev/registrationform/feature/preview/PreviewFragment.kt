package com.afisdev.registrationform.feature.preview

import androidx.fragment.app.activityViewModels
import com.afisdev.common.extension.showConfirmationDialog
import com.afisdev.common.ui.BaseFragment
import com.afisdev.registrationform.R
import com.afisdev.registrationform.databinding.FragmentPreviewBinding
import com.afisdev.registrationform.feature.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by afisdev on 08/09/2023.
 */
@AndroidEntryPoint
class PreviewFragment : BaseFragment<FragmentPreviewBinding, SharedViewModel>() {

    override val viewModel: SharedViewModel by activityViewModels()
    override val layoutResource = R.layout.fragment_preview

    override fun viewDidLoad() {
        binding.fragment = this
        binding.personalDataEntity = viewModel.personalDataEntity.value
        binding.residenceDataEntity = viewModel.residentialDataEntity.value
    }

    fun register() {
        view?.showConfirmationDialog(
            title = getString(R.string.title_confirmation),
            message = getString(R.string.desc_confirmation),
            labelYes = getString(R.string.label_confirm),
            labelNo = getString(R.string.label_cancel),
            onConfirm = {
                viewModel.resetData()
                navigate(PreviewFragmentDirections.actionPreviewFragmentToRegistrationSuccessFragment())
            }
        )
    }
}