package com.weatherapp.skysync.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.weatherapp.skysync.model.Astro
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.widgets.SearchBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val weatherData = produceState<DataOrException<WeatherResponse, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = homeViewModel.getWeatherData(city = "Moscow")
    }.value




    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null ) {
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
                elevation = 2.dp
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
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 80.dp)
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
                .size(200.dp),
            painter = backgroundImage,
            contentDescription = stringResource(id = R.string.condition)
        )

        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 50.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                )
            ) {
                append("$currentTemp°")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                )
            ) {
                append("  $condition")
            }

        }

        Text(text = text)

        HumidityPressureRow(weatherResponse)
        Spacer(modifier = Modifier.height(12.dp))
        SunriseSunsetRow(weatherResponse)
        Divider()


    }

}

@Composable
fun SunriseSunsetRow(weatherResponse: WeatherResponse) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier
            .padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp))
            Text(modifier = Modifier
                .padding(start = 4.dp),
                text = weatherResponse.forecast.forecastday[0].astro.sunrise
            )

        }
        Row(modifier = Modifier
            .padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp))
            Text(modifier = Modifier
                .padding(start = 4.dp),
                text = weatherResponse.forecast.forecastday[0].astro.sunset
            )

        }
    }
}

@Composable
fun HumidityPressureRow(weatherResponse: WeatherResponse) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier
            .padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_water_drop_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp))
            Text(modifier = Modifier
                .padding(start = 4.dp),
                text = "${weatherResponse.current.humidity.toString()}%")

        }
        Row(modifier = Modifier
            .padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_compress_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp))
            Text(modifier = Modifier
                .padding(start = 4.dp),
                text = "${weatherResponse.current.pressure_mb}psi")

        }
        Row(modifier = Modifier
            .padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_air_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp))
            Text(modifier = Modifier
                .padding(start = 4.dp),
                text = "${weatherResponse.current.wind_mph}mph")

        }
    }
}

fun setBackgroundImage(conditionCode: Int): Int {

    return when (conditionCode) {
        1000 -> R.drawable.sunnyy
        in listOf(1003, 1006, 1009, 1030, 1135) -> R.drawable.clouudy
        in listOf(
            1063,
            1072,
            1150,
            1153,
            1180,
            1183,
            1186,
            1189,
            1192,
            1195,
            1198,
            1201,
            1168,
            1240,
            1243,
            1246
        ) -> R.drawable.rainny

        in listOf(
            1066,
            1069,
            1072,
            1117,
            1210,
            1213,
            1216,
            1219,
            1222,
            1225,
            1237,
            1147,
            1171,
            1207,
            1249,
            1252,
            1255,
            1258,
            1264
        ) -> R.drawable.snowy

        in listOf(1087, 1273, 1276, 1279, 1282) -> R.drawable.stormy
        in listOf(1114, 1117) -> R.drawable.windy
        else -> R.drawable.sunnyy
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(inputDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val parsedDate = LocalDate.parse(inputDate, formatter)

    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
    return outputFormatter.format(parsedDate)
}