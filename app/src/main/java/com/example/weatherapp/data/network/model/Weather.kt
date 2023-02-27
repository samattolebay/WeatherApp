package com.example.weatherapp.data.network.model

data class Weather(
    val id: Int,
    val cityName: String,
    val main: String,
    val description: String,
    val icon: String
)
