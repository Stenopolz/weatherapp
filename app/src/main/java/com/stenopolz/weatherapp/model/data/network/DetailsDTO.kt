package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsDTO(
    @field:Json(name = "sunrise")
    val sunriseTimestamp: Long,
    @field:Json(name = "sunset")
    val sunsetTimestamp: Long,
)
