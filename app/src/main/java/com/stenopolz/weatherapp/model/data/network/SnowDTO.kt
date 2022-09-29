package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SnowDTO(
    @field:Json(name = "1h")
    val amountOfSnowLast1h: Float?,
    @field:Json(name = "3h")
    val amountOfSnowLast3h: Float?,
)
