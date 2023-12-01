package com.weatherapp.skysync.network

import com.weatherapp.skysync.model.Astro
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = Constants.API_KEY,
        @Query("q") query: String,
        @Query("days") days: Int = 7,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ) : WeatherResponse



}
