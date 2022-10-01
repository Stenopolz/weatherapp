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
                        val weather = service.getWeather(
                            lat = city.position.lat,
                            lon = city.position.lon,
                            apiKey = BuildConfig.OPEN_WEATHER_MAP_API_KEY
                        )
                        return@async WeatherInfo(
                            cityName = city.name,
                            temperature = weather.mainWeather.temperature,
                            minTemperature = weather.mainWeather.tempMin,
                            maxTemperature = weather.mainWeather.tempMax,
                            feelsLikeTemperature = weather.mainWeather.feelsLikeTemperature,
                            description = weather.weather.first().description,
                            imageUrl = buildIconUrlFromIconCode(weather.weather.first().iconCode)
                        )
                    }

                }
                callsList.awaitAll()
            }
        }
    }

    private fun buildIconUrlFromIconCode(code: String) = "https://openweathermap.org/img/wn/$code@4x.png"
}
