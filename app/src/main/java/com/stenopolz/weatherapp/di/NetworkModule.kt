package com.stenopolz.weatherapp.di

import com.squareup.moshi.Moshi
import com.stenopolz.weatherapp.model.service.WeatherServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {
    private const val defaultTimeout = 10L
    private const val baseUrl = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(defaultTimeout, TimeUnit.SECONDS)
        .connectTimeout(defaultTimeout, TimeUnit.SECONDS)
        .readTimeout(defaultTimeout, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideCountryApi(retrofit: Retrofit): WeatherServiceAPI =
        retrofit.create(WeatherServiceAPI::class.java)
}
