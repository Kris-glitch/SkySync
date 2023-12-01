package com.weatherapp.skysync.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.weatherapp.skysync.data.DataOrException
import com.weatherapp.skysync.model.Astro
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(city: String)
            : DataOrException<WeatherResponse, Boolean, Exception> {
        return weatherRepository.getWeather(city)

    }



}