package com.adyabukhari.tunaikuasessment.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonalData(
    val nomorKtp: String,
    val namaLengkap: String,
    val noRekening: String,
    val tingkatPendidikan: String,
    val tanggalLahir: String
): Parcelable