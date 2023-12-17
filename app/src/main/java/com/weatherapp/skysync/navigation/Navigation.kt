package com.weatherapp.skysync.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.weatherapp.skysync.screens.about.AboutScreen
import com.weatherapp.skysync.screens.favorites.FavoritesScreen
import com.weatherapp.skysync.screens.home.HomeScreen
import com.weatherapp.skysync.screens.home.HomeViewModel
import com.weatherapp.skysync.screens.search.SearchScreen
import com.weatherapp.skysync.screens.settings.SettingsScreen
import com.weatherapp.skysync.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name ) {
        composable(Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        val route = Screens.HomeScreen.name

        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })) {navBack ->

            navBack.arguments?.getString("city").let {city ->
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(homeViewModel, navController = navController, city = city)
            }
        }
        composable(Screens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(Screens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(Screens.FavoritesScreen.name) {
            FavoritesScreen(navController = navController)
        }
        composable(Screens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }

    }
}