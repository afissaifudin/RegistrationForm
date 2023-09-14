package com.afisdev.registrationform.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.afisdev.common.model.ResultState
import com.afisdev.common.ui.BaseViewModel
import com.afisdev.registrationform.domain.MainUseCase
import com.afisdev.registrationform.feature.personaldata.PersonalDataEntity
import com.afisdev.registrationform.feature.residentialdata.ResidentialDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by afisdev on 08/09/2023.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val useCase: MainUseCase,
): BaseViewModel() {

    private val _personalDataEntity = MutableLiveData<PersonalDataEntity?>()
    val personalDataEntity: LiveData<PersonalDataEntity?> = _personalDataEntity

    private val _residentialDataEntity = MutableLiveData<ResidentialDataEntity?>()
    val residentialDataEntity: LiveData<ResidentialDataEntity?> = _residentialDataEntity

    private val _provinceList = MutableLiveData<List<String>>()
    val provinceList: LiveData<List<String>> = _provinceList

    fun setValuePersonalData(personalDataEntity: PersonalDataEntity) {
        _personalDataEntity.value = personalDataEntity
    }

    fun setValueResidentialData(residentialDataEntity: ResidentialDataEntity) {
        _residentialDataEntity.value = residentialDataEntity
    }

    fun resetData() {
        _personalDataEntity.value = null
        _residentialDataEntity.value = null
    }

    fun getProvince() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProvince().collect { result ->
                when(result.status) {
                    ResultState.Status.SUCCESS -> {
                        result.data?.let { provinceListResp ->
                            // need refactor, using mapper
                            _provinceList.postValue(
                                provinceListResp.data.map { it.name.orEmpty() }
                            )
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

}