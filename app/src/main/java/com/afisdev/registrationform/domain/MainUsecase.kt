package com.afisdev.registrationform.domain

import com.afisdev.registrationform.data.repository.MainRepository
import javax.inject.Inject


/**
 * Created by afisdev on 08/09/2023.
 */
class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun getProvince() = mainRepository.getProvince()
}