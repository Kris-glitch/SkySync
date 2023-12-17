package com.weatherapp.skysync.screens.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weatherapp.skysync.R
import com.weatherapp.skysync.model.Settings
import com.weatherapp.skysync.widgets.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    var toggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Metric (C)", "Imperial (F)")
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value

    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0]
    else choiceFromDb[0].unit

    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

    Scaffold(
        topBar = {
            SearchBar(
                navController = navController,
                title = "Settings",
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
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 100.dp),
            color = Color(0xFF2b3f58),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(id = R.string.change_units),
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                )
                Row() {
                    IconToggleButton(
                        checked = !toggleState,
                        onCheckedChange = { unit ->
                            toggleState = !unit
                            choiceState = if (toggleState) {
                                "Imperial (F)"
                            } else {
                                "Metric (C)"
                            }
                            Log.d("TAG", "MainContent: $toggleState")

                        },
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .padding(5.dp)
                            .background(color = Color(0xFF9FC2DA), shape = RoundedCornerShape(8.dp))
                    ) {

                        Text(
                            text = if (toggleState) "Fahrenheit ºF" else "Celsius ºC",
                            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                            fontSize = 18.sp,
                            color = Color(0xFF233947)
                        )


                    }
                    Button(
                        onClick = {
                            settingsViewModel.deleteAllUnits()
                            settingsViewModel.insertUnit(Settings(unit = choiceState))

                        },
                        modifier = Modifier
                            .padding(start = 16.dp, top = 4.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9b5859)
                        )
                    ) {
                        Text(
                            text = "Save",
                            modifier = Modifier.padding(2.dp),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_regular))
                        )

                    }
                }


            }
        }
    }
}

