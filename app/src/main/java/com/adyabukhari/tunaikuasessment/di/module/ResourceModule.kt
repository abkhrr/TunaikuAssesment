package com.adyabukhari.tunaikuasessment.di.module

import com.adyabukhari.tunaikuasessment.utils.helper.appresource.AppResource
import com.adyabukhari.tunaikuasessment.utils.helper.appresource.AppResourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceModule {

    @Provides
    @Singleton
    fun provideAppResource(provider: AppResourceImpl): AppResource {
        return provider
    }
}