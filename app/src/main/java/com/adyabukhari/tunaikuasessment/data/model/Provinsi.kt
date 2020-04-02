package com.adyabukhari.tunaikuasessment.data.model

import com.google.gson.annotations.SerializedName

data class Provinsi(
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val nama: String
)