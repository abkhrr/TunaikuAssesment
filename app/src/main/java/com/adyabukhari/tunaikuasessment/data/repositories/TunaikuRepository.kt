package com.adyabukhari.tunaikuasessment.data.repositories

import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import com.adyabukhari.tunaikuasessment.data.response.ProvinceResponse

interface TunaikuRepository {

    suspend fun getProvince(): Response<ProvinceResponse>

}