package com.adyabukhari.tunaikuasessment.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApp() = application

}