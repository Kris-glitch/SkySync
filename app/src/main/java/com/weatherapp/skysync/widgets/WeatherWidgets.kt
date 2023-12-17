package com.weatherapp.skysync.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.skysync.R
import com.weatherapp.skysync.model.Forecastday
import com.weatherapp.skysync.model.WeatherResponse
import com.weatherapp.skysync.utils.CustomImageSet.setIconImage
import com.weatherapp.skysync.utils.FormattingUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecastRow(
    day: Forecastday,
    unit: String
) {
    var text by remember {
        mutableStateOf("${day.day.maxtemp_c}° / ${day.day.mintemp_c}°")
    }

    text = if (unit == "Metric (C)") {
        "${day.day.maxtemp_c}° / ${day.day.mintemp_c}°"
    } else {
        "${day.day.maxtemp_f}° / ${day.day.mintemp_f}°"
    }

    val condition = day.day.condition.text

    val weatherIcon: Painter = painterResource(id = setIconImage(condition))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        color =  Color.Transparent,
        border = BorderStroke(1.5.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                painter = weatherIcon,
                contentDescription = stringResource(id = R.string.weather_icon)
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = FormattingUtils.formatDay(day.date),
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )


        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritesRow(
    city: String,
    onClick: (city: String) -> Unit,
    onDelete: () -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        color =  Color.Transparent,
        border = BorderStroke(1.5.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp),
                imageVector = Icons.Default.Place,
                contentDescription = stringResource(id = R.string.weather_icon),
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onClick(city) },
                text = city,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )

            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clickable { onDelete() },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )


        }

    }
}

@Composable
fun SunriseSunsetRow(weatherResponse: WeatherResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = weatherResponse.forecast.forecastday[0].astro.sunrise
            )

        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = weatherResponse.forecast.forecastday[0].astro.sunset
            )

        }
    }
}

@Composable
fun HumidityPressureRow(weatherResponse: WeatherResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_water_drop_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = "${weatherResponse.current.humidity.toString()}%"
            )

        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_compress_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = "${weatherResponse.current.pressure_mb}psi"
            )

        }
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_air_24),
                contentDescription = stringResource(id = R.string.humidity),
                modifier = Modifier.size(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = "${weatherResponse.current.wind_mph}mph"
            )

        }
    }
}