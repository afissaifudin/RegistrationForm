package com.afisdev.registrationform.feature

import androidx.lifecycle.Observer
import com.afisdev.common.model.ResultState
import com.afisdev.registrationform.BaseTest
import com.afisdev.registrationform.data.remote.model.ProvinceListResponse
import com.afisdev.registrationform.data.remote.model.ProvinceResponse
import com.afisdev.registrationform.feature.personaldata.PersonalDataEntity
import com.afisdev.registrationform.feature.residentialdata.ResidentialDataEntity
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Test


/**
 * Created by afisdev on 14/09/2023.
 */
@ExperimentalCoroutinesApi
class SharedViewModelTest: BaseTest() {

    private lateinit var viewModel: SharedViewModel

    private val personalDataObserver = mockk<Observer<PersonalDataEntity?>>(relaxed = true)
    private val residentialDataObserver = mockk<Observer<ResidentialDataEntity?>>(relaxed = true)
    private val provinceListObserver = mockk<Observer<List<String>>>(relaxed = true)

    private val samplePersonalDataEntity = PersonalDataEntity(
        "12345",
        "Afis Dev",
        "123456789",
        "S2",
        "01-01-1999"
    )
    private val sampleResidentialDataEntity = ResidentialDataEntity(
        "123 Main St",
        "Home",
        "101",
        "Jawa Barat"
    )

    override fun setUp() {
        super.setUp()
        viewModel = SharedViewModel(mockMainUseCase).apply {
            personalDataEntity.observeForever(personalDataObserver)
            residentialDataEntity.observeForever(residentialDataObserver)
            provinceList.observeForever(provinceListObserver)
        }
    }

    override fun cleanUp() {
        super.cleanUp()
        viewModel.apply {
            personalDataEntity.removeObserver(personalDataObserver)
            residentialDataEntity.removeObserver(residentialDataObserver)
            provinceList.removeObserver(provinceListObserver)
        }
    }

    @Test
    fun `setValuePersonalData should update personalDataEntity LiveData`() {

        // When
        viewModel.setValuePersonalData(samplePersonalDataEntity)

        // Then
        verify {
            personalDataObserver.onChanged(samplePersonalDataEntity)
        }
    }

    @Test
    fun `setValueResidentialData should update residentialDataEntity LiveData`() {

        // When
        viewModel.setValueResidentialData(sampleResidentialDataEntity)

        // Then
        verify {
            residentialDataObserver.onChanged(sampleResidentialDataEntity)
        }
    }

    @Test
    fun `resetData should set personalDataEntity and residentialDataEntity to null`() {
        // Given
        viewModel.setValuePersonalData(samplePersonalDataEntity)
        viewModel.setValueResidentialData(sampleResidentialDataEntity)

        // When
        viewModel.resetData()

        verify{
            personalDataObserver.onChanged(null)
            residentialDataObserver.onChanged(null)
        }
    }

    @Test
    fun `getProvince should update provinceList LiveData`() {
        // Given
        val mockProvinceListResponse = mutableListOf<ProvinceResponse>()
        val mockProvinceList = mutableListOf<String>()

        for (i in 1..10) {
            val province = ProvinceResponse(
                id = i.toString(),
                name = "Provinsi ${generateRandomString()}"
            )
            mockProvinceListResponse.add(province)
            mockProvinceList.add(province.name!!)
        }
        val expectedProvinceListResponse = ProvinceListResponse("Success", "Data fetched successfully", mockProvinceListResponse)
        val successResult = ResultState.success(data = expectedProvinceListResponse)

        coEvery {
            mockMainUseCase.getProvince()
        } returns flow {
            emit(successResult)
        }

        // When
        viewModel.getProvince()

        // Then
        verify {
            provinceListObserver.onChanged(mockProvinceList)
        }

        confirmVerified(provinceListObserver)
    }
}