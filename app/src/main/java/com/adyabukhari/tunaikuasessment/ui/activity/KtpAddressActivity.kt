package com.adyabukhari.tunaikuasessment.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.model.KtpAddress
import com.adyabukhari.tunaikuasessment.data.model.PersonalData
import com.adyabukhari.tunaikuasessment.data.model.Provinsi
import com.adyabukhari.tunaikuasessment.data.resState.KtpAddressRes
import com.adyabukhari.tunaikuasessment.data.resState.ProvinsiDataRes
import com.adyabukhari.tunaikuasessment.di.injection.Injection
import com.adyabukhari.tunaikuasessment.ui.activity.review.FormReviewActivity
import com.adyabukhari.tunaikuasessment.ui.factory.ViewModelFactory
import com.adyabukhari.tunaikuasessment.ui.viewmodel.KtpAddressViewModel
import com.adyabukhari.tunaikuasessment.utils.constant.ResponseCode
import com.adyabukhari.tunaikuasessment.utils.extension.hide
import com.adyabukhari.tunaikuasessment.utils.extension.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_ktp_address.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.ktp_address_layout.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

class KtpAddressActivity : AppCompatActivity() {

    companion object {
        const val PERSONAL_DATA = "personalData"
    }

    private lateinit var viewModel: KtpAddressViewModel
    private var personal: PersonalData? = null
    private lateinit var provinceAdapter: ArrayAdapter<String>
    private val strProvinces = mutableListOf<String>()
    private val provinces = mutableListOf<Provinsi>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<KtpAddressViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktp_address)
        initActionBar()

        Injection.getApp(this.application).inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(KtpAddressViewModel::class.java)

        viewModel.ktpAddressState.observe(this, ktpAddressStateObserver)
        viewModel.provinceListState.observe(this, provinceListStateObserver)

        personal = intent.getParcelableExtra(PERSONAL_DATA)

        ArrayAdapter.createFromResource(
            this,
            R.array.ktp_address_jenis_tempat_tinggal,
            R.layout.spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_item)
                spinner_jenis_tempat_tinggal.adapter = adapter
            }

        provinceAdapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_item,
            strProvinces
        )
            .also {
                it.setDropDownViewResource(R.layout.spinner_dropdown)
                spinner_provinsi.adapter = it
            }

        btn_next.setOnClickListener {
            viewModel.submit(
                alamatKtp = edt_alamat_ktp.text.toString(),
                jenisTempatTinggal = spinner_jenis_tempat_tinggal.getItemAtPosition(
                    spinner_jenis_tempat_tinggal.selectedItemPosition
                ).toString(),
                provinsi = provinces[spinner_provinsi.selectedItemPosition],
                nomorBlok = edt_nomor_blok.text.toString()
            )
        }

        btn_try_again.setOnClickListener {
            viewModel.getProvinces()
        }

        viewModel.getProvinces()
    }

    // Function Belongs Here

    private val ktpAddressStateObserver = Observer<KtpAddressRes> {
        if (it.isSuccess) {
            if (it.ktpAddress != null) {
                toReviewDataStep(it.ktpAddress)
            }
            return@Observer
        }

        showSnackbar(it.message)
    }

    private val provinceListStateObserver = Observer<ProvinsiDataRes> { it ->
        if (it.isLoading) {
            loader.show()
            layout_error.hide()
            sv_ktp_address.hide()
            return@Observer
        }
        if (it.statusCode != ResponseCode.SUCCESS) {
            loader.hide()
            layout_error.show()
            tv_error_message.text = it.message
            sv_ktp_address.hide()
            return@Observer
        }

        loader.hide()
        layout_error.hide()
        sv_ktp_address.show()

        provinces.apply {
            clear()
            add(Provinsi(id = "", nama = getString(R.string.ktp_address_provinsi_default_option)))
            addAll(it.list)
        }
        strProvinces.clear()
        provinces.map {
            strProvinces.add(it.nama)
        }

        provinceAdapter.notifyDataSetChanged()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(sv_ktp_address, message, Snackbar.LENGTH_LONG).show()
    }

    private fun toReviewDataStep(ktpAddress: KtpAddress) {
        val reviewDataIntent = Intent(this, FormReviewActivity::class.java)
        reviewDataIntent.putExtra(FormReviewActivity.PERSONAL_DATA, personal)
        reviewDataIntent.putExtra(FormReviewActivity.KTP_ADDRESS, ktpAddress)
        startActivity(reviewDataIntent)
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

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}