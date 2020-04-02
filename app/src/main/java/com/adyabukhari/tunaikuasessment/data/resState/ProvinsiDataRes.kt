package com.adyabukhari.tunaikuasessment.data.resState

import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import com.adyabukhari.tunaikuasessment.utils.constant.ResponseCode

data class ProvinsiDataRes(
    val isLoading: Boolean = false,
    val statusCode: Int = ResponseCode.SUCCESS,
    val message: String = "",
    val list: List<Provinsi> = mutableListOf()
)