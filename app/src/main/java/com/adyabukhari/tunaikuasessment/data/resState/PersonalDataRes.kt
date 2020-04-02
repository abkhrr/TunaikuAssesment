package com.adyabukhari.tunaikuasessment.data.resState

import com.adyabukhari.tunaikuasessment.data.model.PersonalData

data class PersonalDataRes(
    val message: String = "",
    val isSuccess: Boolean = false,
    val personalData: PersonalData? = null
)