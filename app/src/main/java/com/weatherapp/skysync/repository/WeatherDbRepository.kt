package com.weatherapp.skysync.repository

import com.weatherapp.skysync.data.WeatherDao
import com.weatherapp.skysync.model.Favorites
import com.weatherapp.skysync.model.Settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorites>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorites) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorites) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorites) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorites = weatherDao.getFavById(city)

  fun getUnits(): Flow<List<Settings>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: Settings) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Settings) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: Settings) = weatherDao.deleteUnit(unit)



}