package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RainDTO(
    @field:Json(name = "1h")
    val amountOfRainLast1h: Float?,
    @field:Json(name = "3h")
    val amountOfRainLast3h: Float?,
)
