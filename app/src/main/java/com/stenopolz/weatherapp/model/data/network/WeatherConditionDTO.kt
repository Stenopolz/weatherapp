package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherConditionDTO(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "icon")
    val iconCode: String,
)
