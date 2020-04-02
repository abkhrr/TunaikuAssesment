package com.adyabukhari.tunaikuasessment.di.component

import com.adyabukhari.tunaikuasessment.di.module.*
import com.adyabukhari.tunaikuasessment.ui.activity.KtpAddressActivity
import com.adyabukhari.tunaikuasessment.ui.activity.PersonalDataActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ResourceModule::class
    ])
interface AppComponent {
    fun inject(activity: PersonalDataActivity)

    fun inject(activity: KtpAddressActivity)
}