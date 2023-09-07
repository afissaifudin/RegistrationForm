package com.afisdev.registrationform.data.remote.datasource

import com.afisdev.common.model.ResultState
import com.afisdev.registrationform.data.remote.model.ProvinceListResponse


/**
 * Created by afisdev on 08/09/2023.
 */
interface MainDataSource {

    suspend fun getProvince(): ResultState<ProvinceListResponse>
}