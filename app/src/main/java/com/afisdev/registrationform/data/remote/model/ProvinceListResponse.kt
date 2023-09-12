package com.afisdev.registrationform.data.remote.model

import com.afisdev.common.model.BaseResponse


/**
 * Created by afisdev on 08/09/2023.
 */
data class ProvinceListResponse(

    override var status: String?,

    override var message: String?,

    val data: List<ProvinceResponse>

): BaseResponse