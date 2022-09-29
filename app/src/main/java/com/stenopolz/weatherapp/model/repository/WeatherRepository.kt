package com.stenopolz.weatherapp.model.repository

import com.stenopolz.weatherapp.BuildConfig
import com.stenopolz.weatherapp.extension.CallResult
import com.stenopolz.weatherapp.extension.apiCall
import com.stenopolz.weatherapp.model.data.network.LatLonDTO
import com.stenopolz.weatherapp.model.data.network.WeatherResponseDTO
import com.stenopolz.weatherapp.model.service.WeatherServiceAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: WeatherServiceAPI
) {
    suspend fun fetchWeather(coordinates: LatLonDTO): CallResult<WeatherResponseDTO> {
        return apiCall {
            service.getWeather(
                lat = coordinates.lat,
                lon = coordinates.lon,
                apiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY
            )
        }
    }
}
