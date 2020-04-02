package com.adyabukhari.tunaikuasessment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.model.PersonalData
import com.adyabukhari.tunaikuasessment.data.resState.PersonalDataRes
import com.adyabukhari.tunaikuasessment.utils.helper.NumericRegex
import com.adyabukhari.tunaikuasessment.utils.helper.appresource.AppResource
import javax.inject.Inject

class PersonalDataViewModel @Inject constructor(private val resourceProvider: AppResource) :
    ViewModel() {

    val personalDataState = MutableLiveData<PersonalDataRes>()

    fun submit(
        nomorKtp: String,
        namaLengkap: String,
        nomorRekening: String,
        tingkatPendidikan: String,
        tanggalLahir: String
    ) {
        if (nomorKtp.isBlank()) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_ktp_required_error_message))
            return
        }
        if (nomorKtp.trim().length < 16) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_ktp_min_char_error_message))
            return
        }
        if (nomorKtp.trim().length > 16) {
            personalDataState.value = PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_ktp_max_char_error_message))
            return
        }
        if (!NumericRegex.isNumeric(nomorKtp)) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_ktp_numeric_error_message))
            return
        }
        if (namaLengkap.isBlank()) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nama_lengkap_required_error_message))
            return
        }
        if (namaLengkap.trim().length > 10) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nama_lengkap_max_char_error_message))
            return
        }
        if (nomorRekening.isBlank()) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_rekening_required_error_message))
            return
        }
        if (nomorRekening.trim().length < 8) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_rekening_min_char_error_message))
            return
        }
        if (!NumericRegex.isNumeric(nomorRekening)) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_nomor_rekening_invalid_error_message))
            return
        }
        if (tingkatPendidikan == resourceProvider.getArrayString(R.array.personal_data_tingkat_pendidikan)[0]) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_tingkat_pendidikan_not_selected_error_message))
            return
        }
        if (tanggalLahir.isBlank()) {
            personalDataState.value =
                PersonalDataRes(message = resourceProvider.getStringRes(R.string.personal_data_tanggal_lahir_required_error_message))
            return
        }
        personalDataState.value = PersonalDataRes(
            isSuccess = true,
            personalData = PersonalData(
                nomorKtp, namaLengkap, nomorRekening, tingkatPendidikan, tanggalLahir
            )
        )
    }
}