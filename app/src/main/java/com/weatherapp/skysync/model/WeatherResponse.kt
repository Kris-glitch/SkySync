package com.weatherapp.skysync.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)