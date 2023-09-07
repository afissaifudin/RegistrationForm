package com.afisdev.registrationform.data.remote.model

import com.afisdev.common.model.BaseResponse
import com.google.gson.annotations.SerializedName


/**
 * Created by afisdev on 08/09/2023.
 */
data class ProvinceListResponse(

    @SerializedName("message")
    override var status: String?,

    @SerializedName("message")
    override var message: String?,

    @SerializedName("data")
    val data: List<ProvinceResponse>

): BaseResponse