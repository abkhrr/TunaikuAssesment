package com.adyabukhari.tunaikuasessment.di.module

import com.adyabukhari.tunaikuasessment.ui.factory.ViewModelFactory
import com.adyabukhari.tunaikuasessment.ui.viewmodel.KtpAddressViewModel
import com.adyabukhari.tunaikuasessment.ui.viewmodel.PersonalDataViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule{

    @Provides
    @Singleton
    fun providePersonalDataFactory(viewModel: PersonalDataViewModel): ViewModelFactory<PersonalDataViewModel> {
        return ViewModelFactory { viewModel }
    }

    @Provides
    @Singleton
    fun provideKtpAddressFactory(viewModel: KtpAddressViewModel): ViewModelFactory<KtpAddressViewModel> {
        return ViewModelFactory { viewModel }
    }

}