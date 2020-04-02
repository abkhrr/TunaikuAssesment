package com.adyabukhari.tunaikuasessment.data.resState

import com.adyabukhari.tunaikuasessment.data.model.KtpAddress

data class KtpAddressRes(
    val message: String = "",
    val isSuccess: Boolean = false,
    val ktpAddress: KtpAddress? = null
)