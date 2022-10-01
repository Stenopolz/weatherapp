package com.stenopolz.weatherapp.viewmodel

data class WeatherUiModel(
    val cityName: String,
    val temperature: String,
    val temperatureRange: String,
    val description: String,
    val imageUrl: String
)
