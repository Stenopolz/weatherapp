package com.stenopolz.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stenopolz.weatherapp.extension.CallResult
import com.stenopolz.weatherapp.model.data.application.DispatchersHolder
import com.stenopolz.weatherapp.model.data.application.WeatherInfo
import com.stenopolz.weatherapp.model.data.network.LatLonDTO
import com.stenopolz.weatherapp.model.repository.PreferencesRepository
import com.stenopolz.weatherapp.model.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastListViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val preferences: PreferencesRepository,
    private val dispatchers: DispatchersHolder
) : ViewModel() {
    private val _weatherList = MutableStateFlow<List<WeatherUiModel>>(emptyList())
    val weatherList: StateFlow<List<WeatherUiModel>> = _weatherList

    private val _contentVisible = MutableStateFlow(false)
    val contentVisible: StateFlow<Boolean> = _contentVisible

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    fun onTryAgain() {
        fetchWeather()
    }

    fun fetchWeather() {
        handleLoading()
        viewModelScope.launch {
            withContext(dispatchers.io) {
                val result = repository.fetchWeather(preferences.getCityList())

                when (result) {
                    is CallResult.Success -> {
                        handleSuccess(result.data)
                    }
                    is CallResult.Error -> {
                        handleError()
                    }
                }
            }
        }
    }

    private fun handleLoading() {
        _contentVisible.value = false
        _isError.value = false
        _isLoading.value = true
    }

    private fun handleSuccess(weatherList: List<WeatherInfo>) {
        _weatherList.value = weatherList.map {
            WeatherUiModel(cityName = it.cityName)
        }
        _contentVisible.value = true
        _isError.value = false
        _isLoading.value = false
    }

    private fun handleError() {
        _contentVisible.value = false
        _isError.value = true
        _isLoading.value = false
    }
}
