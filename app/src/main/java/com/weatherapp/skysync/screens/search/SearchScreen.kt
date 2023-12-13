package com.weatherapp.skysync.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weatherapp.skysync.widgets.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {

    var city by rememberSaveable {
        mutableStateOf("")
    }
    val valid = remember(city) {
        city.trim().isNotEmpty()
    }

    Scaffold(topBar = {
        SearchBar(
            elevation = 2.dp,
            title = "Search City",
            isHomeScreen = false,
            navController = navController,
            enabled = true,
            onBackButtonClicked = { navController.popBackStack() },
            onValueChanged = {inputCity ->
                city = inputCity
            }
            )

    }) {

        Box(modifier = Modifier.padding(it)) {}

    }

}