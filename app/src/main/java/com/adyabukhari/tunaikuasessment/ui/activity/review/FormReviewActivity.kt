package com.adyabukhari.tunaikuasessment.ui.activity.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.model.KtpAddress
import com.adyabukhari.tunaikuasessment.data.model.PersonalData
import com.adyabukhari.tunaikuasessment.ui.activity.PersonalDataActivity
import com.adyabukhari.tunaikuasessment.ui.activity.PersonalDataActivity_MembersInjector
import kotlinx.android.synthetic.main.review_data_layout.*

class FormReviewActivity : AppCompatActivity() {

    companion object {
        const val PERSONAL_DATA = "personalData"
        const val KTP_ADDRESS = "ktpAddress"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_review)

        val personalData = intent.getParcelableExtra<PersonalData>(PERSONAL_DATA)
        val ktpAddress = intent.getParcelableExtra<KtpAddress>(KTP_ADDRESS)

        initView(personalData, ktpAddress)
    }

    private fun initView(personalData: PersonalData?, ktpAddress: KtpAddress?){
        tv_nomor_ktp.text = personalData?.nomorKtp
        tv_nama_lengkap.text = personalData?.namaLengkap
        tv_nomor_rekening.text = personalData?.noRekening
        tv_tingkat_pendidikan.text = personalData?.tingkatPendidikan
        tv_tanggal_lahir.text = personalData?.tanggalLahir

        tv_alamat_ktp.text = ktpAddress?.alamatKtp
        tv_jenis_tempat_tinggal.text = ktpAddress?.jenisTempatTinggal
        tv_nomor_blok.text = ktpAddress?.nomorBlok
        tv_provinsi.text = ktpAddress?.provinsi
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun backToMain(v: View) {
        val confirmIntent = Intent(this@FormReviewActivity, PersonalDataActivity::class.java)
        startActivity(confirmIntent)
        finish()
    }
}
