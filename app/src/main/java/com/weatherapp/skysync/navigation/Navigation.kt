package com.weatherapp.skysync.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weatherapp.skysync.screens.home.HomeScreen
import com.weatherapp.skysync.screens.home.HomeViewModel
import com.weatherapp.skysync.screens.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ) {
        composable(Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(Screens.HomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel, navController = navController)
        }

    }
}