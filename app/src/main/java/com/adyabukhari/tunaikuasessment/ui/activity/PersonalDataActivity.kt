package com.adyabukhari.tunaikuasessment.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.data.model.PersonalData
import com.adyabukhari.tunaikuasessment.data.resState.PersonalDataRes
import com.adyabukhari.tunaikuasessment.di.injection.Injection
import com.adyabukhari.tunaikuasessment.ui.factory.ViewModelFactory
import com.adyabukhari.tunaikuasessment.ui.viewmodel.PersonalDataViewModel
import com.adyabukhari.tunaikuasessment.utils.helper.locale.DateTime
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.personal_data_layout.*
import java.util.*
import javax.inject.Inject

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonalDataViewModel
    private val calendar = Calendar.getInstance()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PersonalDataViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        Injection.getApp(this.application).inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(PersonalDataViewModel::class.java)
        viewModel.personalDataState.observe(this, personalDataStateObserver)

        ArrayAdapter.createFromResource(
            this,
            R.array.personal_data_tingkat_pendidikan,
            R.layout.spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_dropdown)
                spinner_tingkat_pendidikan.adapter = adapter
            }

        tv_birth_date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    calendar.apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, day)
                    }

                    tv_birth_date.text = DateTime.convertCalendarToCustomFormatDate(calendar)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
            datePickerDialog.show()
        }

        btn_next.setOnClickListener {
            viewModel.submit(
                namaLengkap = edt_nama_lengkap.text.toString(),
                nomorKtp = edt_nomor_ktp.text.toString(),
                nomorRekening = edt_nomor_rekening.text.toString(),
                tanggalLahir = tv_birth_date.text.toString(),
                tingkatPendidikan = spinner_tingkat_pendidikan.getItemAtPosition(
                    spinner_tingkat_pendidikan.selectedItemPosition
                ).toString()
            )
        }
    }

    private val personalDataStateObserver = Observer<PersonalDataRes> {
        if (it.isSuccess) {
            if (it.personalData != null) {
                toKtpAddressStep(it.personalData)
            }
            return@Observer
        }

        showSnackbar(it.message)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(sv_personal_data, message, Snackbar.LENGTH_LONG).show()
    }

    private fun toKtpAddressStep(personalData: PersonalData) {
        val ktpAddressIntent = Intent(this, KtpAddressActivity::class.java)
        ktpAddressIntent.putExtra(KtpAddressActivity.PERSONAL_DATA, personalData)
        startActivity(ktpAddressIntent)
    }
}