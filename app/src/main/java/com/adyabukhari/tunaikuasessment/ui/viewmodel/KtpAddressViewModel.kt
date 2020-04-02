package com.adyabukhari.tunaikuasessment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.model.KtpAddress
import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import com.adyabukhari.tunaikuasessment.data.repositories.Response
import com.adyabukhari.tunaikuasessment.data.repositories.TunaikuRepository
import com.adyabukhari.tunaikuasessment.data.resState.KtpAddressRes
import com.adyabukhari.tunaikuasessment.data.resState.ProvinsiDataRes
import com.adyabukhari.tunaikuasessment.utils.helper.appresource.AppResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class KtpAddressViewModel @Inject constructor(
    private val mAppResource: AppResource,
    private val repository: TunaikuRepository
) :
    ViewModel() {

    val ktpAddressState = MutableLiveData<KtpAddressRes>()
    val provinceListState = MutableLiveData<ProvinsiDataRes>()

    fun submit(
        alamatKtp: String,
        jenisTempatTinggal: String,
        nomorBlok: String,
        provinsi: Provinsi
    ) {
        if (alamatKtp.isBlank()) {
            ktpAddressState.value =
                KtpAddressRes(message = mAppResource.getStringRes(R.string.ktp_address_alamat_ktp_required_error_message))
            return
        }
        if (alamatKtp.trim().length > 100) {
            ktpAddressState.value =
                KtpAddressRes(message = mAppResource.getStringRes(R.string.ktp_address_alamat_ktp_max_char_error_message))
            return
        }
        if (jenisTempatTinggal == mAppResource.getArrayString(R.array.ktp_address_jenis_tempat_tinggal)[0]) {
            ktpAddressState.value =
                KtpAddressRes(message = mAppResource.getStringRes(R.string.ktp_address_jenis_tempat_tinggal_not_selected_error_message))
            return
        }
        if (nomorBlok.isBlank()) {
            ktpAddressState.value =
                KtpAddressRes(message = mAppResource.getStringRes(R.string.ktp_address_nomor_blok_required_error_message))
            return
        }
        if (provinsi.id.isEmpty()) {
            ktpAddressState.value =
                KtpAddressRes(message = mAppResource.getStringRes(R.string.ktp_address_provinsi_not_selected_error_message))
            return
        }
        ktpAddressState.value = KtpAddressRes(
            isSuccess = true,
            ktpAddress = KtpAddress(
                alamatKtp, nomorBlok, provinsi.nama, jenisTempatTinggal
            )
        )
    }

    fun getProvinces() {
        provinceListState.value = ProvinsiDataRes(isLoading = true)

        viewModelScope.launch {
            val result = repository.getProvince()

            when (result) {
                is Response.Success -> provinceListState.postValue(ProvinsiDataRes(list = result.response.provinsi))
                is Response.Error -> provinceListState.postValue(
                    ProvinsiDataRes(
                        statusCode = result.code,
                        message = result.message
                    )
                )
            }
        }
    }
}