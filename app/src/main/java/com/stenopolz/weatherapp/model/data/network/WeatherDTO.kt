package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDTO(
    @field:Json(name = "temp")
    val temperature: Float,
    @field:Json(name = "feels_like")
    val feelsLikeTemperature: Float,
    @field:Json(name = "temp_min")
    val tempMin: Float,
    @field:Json(name = "temp_max")
    val tempMax: Float,
    @field:Json(name = "pressure")
    val pressure: Float,
    @field:Json(name = "humidity")
    val humidity: Float,
    @field:Json(name = "sea_level")
    val seaLevelPressure: Float?,
    @field:Json(name = "grnd_level")
    val groundLevelPressure: Float?,
)
