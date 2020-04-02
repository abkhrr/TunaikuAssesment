package com.adyabukhari.tunaikuasessment.di.injection

import android.app.Application
import com.adyabukhari.tunaikuasessment.di.component.AppComponent
import com.adyabukhari.tunaikuasessment.di.component.DaggerAppComponent
import com.adyabukhari.tunaikuasessment.di.module.AppModule

object Injection {
    fun getApp(application: Application): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }
}