package com.adyabukhari.tunaikuasessment.data.repositories

import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.response.ProvinceResponse
import com.adyabukhari.tunaikuasessment.data.service.ApiService
import com.adyabukhari.tunaikuasessment.utils.constant.ResponseCode
import com.adyabukhari.tunaikuasessment.utils.helper.appresource.AppResource
import java.io.IOException
import javax.inject.Inject

class TunaikuRepositoryImpl @Inject constructor(
    private val mApiService: ApiService,
    private val appResource: AppResource
) : TunaikuRepository {

    override suspend fun getProvince(): Response<ProvinceResponse> {
        return try {
            val output = mApiService.getProvince()

            if (!output.error){
                Response.Success(
                    ProvinceResponse(
                        error = output.error,
                        message = output.message,
                        provinsi = output.provinsi
                    )
                )
            }
            else {
                Response.Error(
                    ResponseCode.UNKNOWN_ERROR,
                    output.message
                )
            }
        }
        catch (e: IOException){
            Response.Error(
                ResponseCode.ERROR_NETWORK,
                appResource.getStringRes(R.string.ERROR_NETWORK)
            )
        }
        catch (e: IOException){
            Response.Error(
                ResponseCode.UNKNOWN_ERROR,
                appResource.getStringRes(R.string.UNKNOWN_ERROR)
            )
        }
    }

}