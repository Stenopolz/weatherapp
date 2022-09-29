package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseDTO(
    @field:Json(name = "coord")
    val coordinates: LatLonDTO,
    @field:Json(name = "weather")
    val weather: List<WeatherConditionDTO>,
    @field:Json(name = "main")
    val mainWeather: WeatherDTO,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "wind")
    val wind: WindDTO,
    @field:Json(name = "clouds")
    val clouds: CloudsDTO,
    @field:Json(name = "rain")
    val rain: RainDTO?,
    @field:Json(name = "snow")
    val snow: SnowDTO?,
    @field:Json(name = "sys")
    val details: DetailsDTO
)
