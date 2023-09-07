package com.afisdev.registrationform.data.remote.datasource

import com.afisdev.common.datasource.DataSourceHelper.getResult
import com.afisdev.common.model.ResultState
import com.afisdev.registrationform.data.remote.api.ApiService
import com.afisdev.registrationform.data.remote.model.ProvinceListResponse
import javax.inject.Inject


/**
 * Created by afisdev on 08/09/2023.
 */
class MainDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): MainDataSource {

    override suspend fun getProvince(): ResultState<ProvinceListResponse> {
        return getResult { apiService.getProvince() }
    }

}