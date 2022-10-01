package com.stenopolz.weatherapp.di

import android.content.Context
import com.stenopolz.weatherapp.model.data.application.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object PlatformModule {
    @Provides
    fun provideResourceProvider(
        @ApplicationContext
        context: Context
    ): ResourceProvider = ResourceProvider.DefaultResourceProvider(context)
}
