package com.weatherapp.skysync.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun WeeklyForecastRow(day: Forecastday) {

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
                text = "${day.day.maxtemp_c}° / ${day.day.mintemp_c}°",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
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