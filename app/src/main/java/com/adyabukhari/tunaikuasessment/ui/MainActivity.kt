package com.adyabukhari.tunaikuasessment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adyabukhari.tunaikuasessment.R
import com.adyabukhari.tunaikuasessment.ui.activity.PersonalDataActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Making Splash Screen
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            toPersonalData()
        }
    }

    private suspend fun toPersonalData() = withContext(Dispatchers.Main) {
        val personalDataIntent = Intent(this@MainActivity, PersonalDataActivity::class.java)
        startActivity(personalDataIntent)
        finish()
    }
}
