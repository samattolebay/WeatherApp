package com.example.weatherapp.data.network

interface MainRepository {

    suspend fun fetchCityWeatherByName(city: String)

    suspend fun fetchCityWeatherByPoint(lat: String, lon: String)
}
