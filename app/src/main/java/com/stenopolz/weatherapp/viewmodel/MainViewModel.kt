package com.stenopolz.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stenopolz.weatherapp.extension.CallResult
import com.stenopolz.weatherapp.model.data.application.DispatchersHolder
import com.stenopolz.weatherapp.model.data.network.LatLonDTO
import com.stenopolz.weatherapp.model.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatchers: DispatchersHolder
) : ViewModel() {
    fun fetchData() {
        viewModelScope.launch {
            withContext(dispatchers.io) {
                val result = repository.fetchWeather(
                    LatLonDTO(lat = 59.3251172, lon = 18.0710935)
                )

                when(result) {
                    is CallResult.Success -> {
                        Log.d("Stenopolz", "${result.data}")
                    }
                    is CallResult.Error -> {
                        Log.d("Stenopolz", "${result.exception.message}")
                    }
                }
            }
        }
    }
}
