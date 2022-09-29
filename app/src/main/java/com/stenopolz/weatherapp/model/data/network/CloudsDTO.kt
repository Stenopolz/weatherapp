package com.stenopolz.weatherapp.model.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CloudsDTO(
    @field:Json(name = "all")
    val coverage: Int,
)
