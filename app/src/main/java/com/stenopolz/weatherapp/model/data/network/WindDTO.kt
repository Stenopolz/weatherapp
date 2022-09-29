package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindDTO(
    @field:Json(name = "speed")
    val speed: Float,
    @field:Json(name = "deg")
    val deg: Int,
    @field:Json(name = "gust")
    val gust: Float?,
)
