package com.afisdev.registrationform.data.remote.model

import com.google.gson.annotations.SerializedName


/**
 * Created by afisdev on 08/09/2023.
 */
data class ProvinceResponse(

    @SerializedName("id")
    var id: String?,

    @SerializedName("message")
    var name: String?,
)