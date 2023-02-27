package com.example.weatherapp.data

import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.data.network.model.CityWeatherForecast
import com.example.weatherapp.utils.ResultOf

interface MainRepository {

    suspend fun fetchCityWeatherByName(city: String): ResultOf<CityWeather>

    suspend fun fetchCityWeatherByPoint(lat: String, lon: String): ResultOf<CityWeather>

    suspend fun fetchCityWeatherForecastByPoint(
        lat: String,
        lon: String
    ): ResultOf<CityWeatherForecast>
}
