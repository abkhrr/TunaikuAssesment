package com.adyabukhari.tunaikuasessment.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KtpAddress(
    val alamatKtp: String,
    val jenisTempatTinggal: String,
    val nomorBlok: String,
    val provinsi: String
): Parcelable