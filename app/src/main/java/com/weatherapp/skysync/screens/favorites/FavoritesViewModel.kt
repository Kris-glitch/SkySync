package com.weatherapp.skysync.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.skysync.model.Favorites
import com.weatherapp.skysync.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository)
    : ViewModel(){
    private val _favList = MutableStateFlow<List<Favorites>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged()
                .collect { listOfFavorites ->
                    if (listOfFavorites.isEmpty()) {
                        Log.d("TAG", ": Empty favorites ")
                    }else {
                        _favList.value = listOfFavorites
                    }
                }
        }
    }
    fun insertFavorite(city: String, country: String) = viewModelScope.launch {
        repository.insertFavorite(Favorites(city, country))
        Log.d("TAG", "city saved : $city")
    }
    fun updateFavorite(favorite: Favorites) = viewModelScope.launch { repository.updateFavorite(favorite) }
    fun deleteFavorite(favorite: Favorites) = viewModelScope.launch { repository.deleteFavorite(favorite) }


}