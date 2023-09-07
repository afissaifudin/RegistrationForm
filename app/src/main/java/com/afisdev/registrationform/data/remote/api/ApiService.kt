package com.afisdev.registrationform.data.remote.api

import com.afisdev.registrationform.data.remote.model.ProvinceListResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by afisdev on 08/09/2023.
 */
interface ApiService {

    @GET("v1/regional/provinsi")
    suspend fun getProvince(): Response<ProvinceListResponse>
}