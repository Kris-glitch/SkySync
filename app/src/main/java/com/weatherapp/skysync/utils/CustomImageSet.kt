package com.weatherapp.skysync.utils

import com.weatherapp.skysync.R

object CustomImageSet {
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

    fun setIconImage(condition: String): Int {
        return when (condition) {
            in listOf("Cloudy", "Partly cloudy", "Overcast", "Mist") -> R.drawable.cloudy

            in listOf(
                "Patchy rain possible",
                "Fog",
                "Patchy light drizzle",
                "Light drizzle",
                "Patchy light rain",
                "Light rain",
                "Moderate rain at times",
                "Light rain shower",
                "Light sleet showers"
            ) -> R.drawable.rain

            in listOf(
                "Moderate rain",
                "Heavy rain at times",
                "Heavy rain",
                "Moderate or heavy freezing rain",
                "Moderate or heavy rain shower",
                "Torrential rain shower",
                "Moderate or heavy sleet showers"
            ) -> R.drawable.heavyrain

            in listOf("Blizzard") -> R.drawable.wind

            in listOf(
                "Patchy snow possible",
                "Patchy sleet possible",
                "Patchy freezing drizzle possible",
                "Freezing fog",
                "Freezing drizzle",
                "Light freezing rain",
                "Light sleet",
                "Moderate or heavy sleet",
                "Patchy light snow",
                "Light snow",
                "Patchy moderate snow",
                "Moderate snow",
                "Ice pellets",
                "Light snow showers",
                "Light showers of ice pellets"
            ) -> R.drawable.snow

            in listOf("Blowing snow", "Heavy freezing drizzle", "Patchy heavy snow", "Heavy snow",
                "Moderate or heavy snow showers", "Moderate or heavy showers of ice pellets") -> R.drawable.heavysnow
            in listOf("Thundery outbreaks possible", "Patchy light rain with thunder", "Patchy light snow with thunder") -> R.drawable.tunder
            in listOf("Moderate or heavy rain with thunder", "Moderate or heavy snow with thunder") -> R.drawable.storm
            else -> R.drawable.sunny
        }
    }
}