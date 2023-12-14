package com.weatherapp.skysync.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.skysync.R
import androidx.navigation.NavController
import com.weatherapp.skysync.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    title: String,
    isHomeScreen: Boolean,
    showSearch: Boolean,
    elevation: Dp,
    navController: NavController,
    enabled: Boolean,
    onBackButtonClicked: () -> Unit = {},
    onMoreClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onValueChanged: (String) -> Unit = {},
    onAction: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next,

    ) {

    var city by rememberSaveable {
        mutableStateOf(title)
    }
    var showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
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
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = imeAction),
                keyboardActions = onAction,


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
            if(showSearch) {
                Icon(
                    modifier = Modifier.clickable { onSearchClicked() },
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon)
                )
            }
            if (isHomeScreen) {
                Icon(
                    modifier = Modifier.clickable {
                        onMoreClicked()
                        showDialog.value = true
                                                  },
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

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    val items = listOf("About", "Favorites", "Settings")

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        DropdownMenu(expanded = expanded ,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .width(140.dp)
                .background(Color(0xFF2b3f58))) {
            items.forEachIndexed { _, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                },
                    leadingIcon = {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings

                            }, contentDescription = null,
                            tint = Color.White
                        )
                    },
                    text = {
                        Text(text = text,
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    when (text) {
                                        "About" -> Screens.AboutScreen.name
                                        "Favorites" -> Screens.FavouritesScreen.name
                                        else -> Screens.SettingsScreen.name
                                    })


                            },
                            fontWeight = FontWeight.W300,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.noto_sans_regular))
                        )
                    }
                )
            }



        }

    }

}
