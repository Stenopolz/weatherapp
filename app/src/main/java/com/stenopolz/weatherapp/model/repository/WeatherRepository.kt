package com.stenopolz.weatherapp.model.repository

import com.stenopolz.weatherapp.BuildConfig
import com.stenopolz.weatherapp.extension.CallResult
import com.stenopolz.weatherapp.extension.apiCall
import com.stenopolz.weatherapp.model.data.application.CityInfo
import com.stenopolz.weatherapp.model.data.application.WeatherInfo
import com.stenopolz.weatherapp.model.service.WeatherServiceAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: WeatherServiceAPI
) {
    suspend fun fetchWeather(cities: List<CityInfo>): CallResult<List<WeatherInfo>> {
        return apiCall {
            coroutineScope {
                val callsList = cities.map { city ->
                    async {
                        service.getWeather(
                            lat = city.position.lat,
                            lon = city.position.lon,
                            apiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY
                        )
                        return@async WeatherInfo(cityName = city.name)
                    }

                }
                callsList.awaitAll()
            }
        }
    }
}
