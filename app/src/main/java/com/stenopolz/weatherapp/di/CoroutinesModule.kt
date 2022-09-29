package com.stenopolz.weatherapp.di

import com.stenopolz.weatherapp.model.data.application.DispatchersHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object CoroutinesModule {
    @Provides
    fun provideDispatchersHolder(): DispatchersHolder =
        DispatchersHolder(
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            default = Dispatchers.Default
        )
}
