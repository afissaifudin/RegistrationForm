package com.afisdev.registrationform.data.repository

import com.afisdev.common.model.ResultState
import com.afisdev.registrationform.data.remote.model.ProvinceListResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by afisdev on 08/09/2023.
 */
interface MainRepository {

    suspend fun getProvince(): Flow<ResultState<ProvinceListResponse>>
}