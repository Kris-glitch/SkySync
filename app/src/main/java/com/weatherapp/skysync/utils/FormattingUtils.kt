package com.weatherapp.skysync.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object FormattingUtils{
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(inputDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val parsedDate = LocalDate.parse(inputDate, formatter)

        val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
        return outputFormatter.format(parsedDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDay(inputDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val parsedDate = LocalDate.parse(inputDate, formatter)

        val outputFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
        return outputFormatter.format(parsedDate)
    }
}