package com.stenopolz.weatherapp.model.repository

import com.stenopolz.weatherapp.model.data.application.CityInfo
import com.stenopolz.weatherapp.model.data.application.LatLon
import javax.inject.Inject

/*
    Ideally this will be stored in some database or shared preferences or downloaded from server.
    I decided to keep it simple for now.
    But this abstraction will make it simple to change the source of data in the future
 */
class PreferencesRepository @Inject constructor() {

    fun getCityList(): List<CityInfo> {
        return cityList
    }

    private val cityList = listOf(
        CityInfo(name = "Gothenburg", position = LatLon(lat = 57.7072326, lon = 11.9670171)),
        CityInfo(name = "Stockholm", position = LatLon(lat = 59.3251172, lon = 18.0710935)),
        CityInfo(name = "Mountain View", position = LatLon(lat = 38.0088105, lon = -122.1174648)),
        CityInfo(name = "London", position = LatLon(lat = 51.5073219, lon = -0.1276474)),
        CityInfo(name = "New York", position = LatLon(lat = 40.7127281, lon = -74.0060152)),
        CityInfo(name = "Berlin", position = LatLon(lat = 52.5170365, lon = 13.3888599))
    )
}
