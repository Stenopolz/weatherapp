package com.stenopolz.weatherapp.model.data.application

data class WeatherInfo(
    val cityName: String,
    val temperature: Float,
    val minTemperature: Float,
    val maxTemperature: Float,
    val feelsLikeTemperature: Float,
    val description: String,
    val imageUrl: String
)
