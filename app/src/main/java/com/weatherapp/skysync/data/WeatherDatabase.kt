package com.weatherapp.skysync.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weatherapp.skysync.model.Favorites
import com.weatherapp.skysync.model.Settings

@Database(entities = [Favorites::class, Settings::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}