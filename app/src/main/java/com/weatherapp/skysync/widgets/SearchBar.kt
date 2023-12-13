package com.weatherapp.skysync.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.skysync.R
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    title: String,
    isHomeScreen: Boolean,
    elevation: Dp,
    navController: NavController,
    enabled: Boolean,
    onBackButtonClicked: () -> Unit = {},
    onMoreClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onValueChanged: (String) -> Unit = {}

    ) {

   var city by rememberSaveable {
        mutableStateOf(title)
    }

    TopAppBar(
        modifier = Modifier
            .shadow(
                elevation = elevation
            )
            .padding(3.dp),
        title = {
            TextField(
                value = city,
                onValueChange = {
                city = it
                onValueChanged(city)},
                enabled = enabled,
                colors  = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    textColor = MaterialTheme.colorScheme.secondary),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 20.sp
                )
                )
        },
        navigationIcon = {
            if (!isHomeScreen) {
                Icon(
                    modifier = Modifier.clickable { onBackButtonClicked() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_arrow))
            } else {
                Box{}
            }
        },
        actions = {
            Icon(
                modifier = Modifier.clickable { onSearchClicked() },
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon))
            if (isHomeScreen) {
                Icon(
                    modifier = Modifier.clickable { onMoreClicked() },
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = stringResource(id = R.string.more_icon))
            } else {
                Box{}
            }

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            Color.Transparent,
            Color.Transparent,
            Color(0xFF233947),
            Color(0xFF233947),
            Color.DarkGray
        ),

        )



}