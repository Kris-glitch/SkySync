package com.weatherapp.skysync.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weatherapp.skysync.data.DataOrException
import com.weatherapp.skysync.model.WeatherResponse

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val weatherData = produceState<DataOrException<WeatherResponse, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = homeViewModel.getWeatherData(city = "Skopje")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null) {
        TopWeatherBar(weatherData.data!!, navController)

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopWeatherBar(weatherResponse: WeatherResponse, navController: NavController) {
    Scaffold(
        topBar = {

        }
    ) {
        Box(modifier = Modifier.padding(it)){}
        Content(weatherResponse)
    }
}

@Composable
fun Content(
    weatherResponse: WeatherResponse
) {

}