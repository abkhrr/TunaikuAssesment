package com.adyabukhari.tunaikuasessment.di.module

import com.adyabukhari.tunaikuasessment.data.repositories.TunaikuRepository
import com.adyabukhari.tunaikuasessment.data.repositories.TunaikuRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideProvinceRepository(provinceRepository: TunaikuRepositoryImpl): TunaikuRepository {
        return provinceRepository
    }
}