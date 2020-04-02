package com.adyabukhari.tunaikuasessment.data.response

import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("semuaprovinsi")
    val provinsi: List<Provinsi>
)