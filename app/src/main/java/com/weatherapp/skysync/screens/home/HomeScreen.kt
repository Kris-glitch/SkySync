package com.weatherapp.skysync.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weatherapp.skysync.R
import com.weatherapp.skysync.data.DataOrException
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.navigation.Screens
import com.weatherapp.skysync.utils.CustomImageSet.setBackgroundImage
import com.weatherapp.skysync.utils.FormattingUtils.formatDate
import com.weatherapp.skysync.widgets.HumidityPressureRow
import com.weatherapp.skysync.widgets.SearchBar
import com.weatherapp.skysync.widgets.SunriseSunsetRow
import com.weatherapp.skysync.widgets.WeeklyForecastRow


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    city: String?
) {
    val weatherData = produceState<DataOrException<WeatherResponse, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = homeViewModel.getWeatherData(city = city ?: "Skopje")
    }.value




    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        TopWeatherBar(weatherData.data!!, navController)

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopWeatherBar(
    weatherResponse: WeatherResponse,
    navController: NavController
) {

    Scaffold(
        topBar = {
            SearchBar(
                navController = navController,
                title = weatherResponse.location.name + ", " + weatherResponse.location.country,
                elevation = 2.dp,
                enabled = false,
                isHomeScreen = true,
                showSearch = true,
                onBackButtonClicked = {

                },
                onMoreClicked = {

                },
                onSearchClicked = {
                    navController.navigate(Screens.SearchScreen.name)
                },
                onAction = KeyboardActions.Default

            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {}
        Content(weatherResponse)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    weatherResponse: WeatherResponse
) {
    val conditionCode = weatherResponse.current.condition.code

    val inputDate = weatherResponse.forecast.forecastday[0].date

    val condition = weatherResponse.current.condition.text

    val currentTemp = weatherResponse.current.temp_c.toString()

    val backgroundImage: Painter = painterResource(id = setBackgroundImage(conditionCode))

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 80.dp)
                .fillMaxWidth(),
            color = Color(0xFF9FC2DA)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = formatDate(inputDate),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular))

                )

                Image(
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(230.dp),
                    painter = backgroundImage,
                    contentDescription = stringResource(id = R.string.condition)
                )

                val text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 40.sp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                        )
                    ) {
                        append("$currentTemp°")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_regular))
                        )
                    ) {
                        append("  $condition")
                    }

                }

                Text(text = text)
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                HumidityPressureRow(weatherResponse)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                SunriseSunsetRow(weatherResponse)
                Spacer(modifier = Modifier.height(6.dp))
                Divider()
                Spacer(modifier = Modifier.height(20.dp))

            }

        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFF2b3f58),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {

            LazyColumn(
                modifier = Modifier.padding(3.dp),
                contentPadding = PaddingValues(3.dp)
            ) {
                items(items = weatherResponse.forecast.forecastday, itemContent = { day ->
                    WeeklyForecastRow(day)
                })

            }

        }


    }


}





