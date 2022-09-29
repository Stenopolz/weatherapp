package com.stenopolz.weatherapp.model.service

import com.stenopolz.weatherapp.model.data.network.WeatherResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceAPI {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("appid")
        apiKey: String,
        @Query("units")
        units: String = "metric",
    ): WeatherResponseDTO
}
