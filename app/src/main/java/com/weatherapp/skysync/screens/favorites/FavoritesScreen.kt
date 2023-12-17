package com.weatherapp.skysync.screens.favorites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weatherapp.skysync.model.Favorites
import com.weatherapp.skysync.navigation.Screens
import com.weatherapp.skysync.widgets.FavoritesRow
import com.weatherapp.skysync.widgets.SearchBar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
    ) {

    fun onDelete(favorite: Favorites) {
        favoriteViewModel.deleteFavorite(favorite)
    }
    fun onClick(city: String) {
        navController.navigate(Screens.HomeScreen.name + "/$city")
    }

    val favorites = favoriteViewModel.favList.collectAsState().value

    Scaffold(
        topBar = {
            SearchBar(
                navController = navController,
                title = "Favorites",
                elevation = 2.dp,
                enabled = false,
                isHomeScreen = false,
                showSearch = false,
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                onMoreClicked = {},
                onSearchClicked = {},
                onAction = KeyboardActions.Default

            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {}
        Content(favorites, ::onDelete, ::onClick)
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    favList: List<Favorites>,
    onDelete: (favourite: Favorites) -> Unit,
    onClick: (city: String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 100.dp) ,
        color = Color(0xFF2b3f58),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {

        LazyColumn(
            modifier = Modifier.padding(3.dp),
            contentPadding = PaddingValues(3.dp)
        ) {
            items(items = favList, itemContent = { favorite ->
                FavoritesRow(city = favorite.city,onClick = { city ->
                    onClick(city)
                } ){
                    onDelete(favorite)
                }
            })

        }

    }
}