package com.weatherapp.skysync.repository

import com.weatherapp.skysync.data.DataOrException
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi){

    suspend fun getWeather(city: String)
    : DataOrException<WeatherResponse, Boolean, Exception> {
        val response = try {
            weatherApi.getWeather(query = city)
        } catch (exception: Exception) {
            return DataOrException(exception = exception)
        }
        return DataOrException(response)
    }

}