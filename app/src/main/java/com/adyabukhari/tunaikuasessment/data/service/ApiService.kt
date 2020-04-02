package com.adyabukhari.tunaikuasessment.data.service

import com.adyabukhari.tunaikuasessment.data.response.ProvinceResponse
import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    // Retrofit GET API ENDPOINT
    @GET("provinsi")
    suspend fun getProvince(): ProvinceResponse
}