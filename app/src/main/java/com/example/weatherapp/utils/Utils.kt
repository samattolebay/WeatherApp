package com.example.weatherapp.utils

import com.example.weatherapp.data.network.model.*
import com.example.weatherapp.ui.model.CityWeatherViewData

fun getFakeCityWeatherViewData() = CityWeatherViewData(
    coord = Coordinate(lon = 76.95, lat = 43.25),
    weather = arrayOf(
        Weather(
            id = 803,
            main = "Clouds",
            description = "nublado",
            icon = "04"
        )
    ),
    sys = Sys(country = "KZ"),
    main = Main(temp = -3.05f, feelsLike = 0.0f, humidity = 80),
    wind = Wind(speed = 1.0f),
    name = "Almaty",
    forecast = arrayOf(
        DayWeather(
            arrayOf(
                Weather(
                    id = 803,
                    main = "Clouds",
                    description = "nublado",
                    icon = "04"
                )
            ),
            Temp(day = -3.05f)
        ),
        DayWeather(
            arrayOf(
                Weather(
                    id = 803,
                    main = "Clouds",
                    description = "nublado",
                    icon = "04"
                )
            ),
            Temp(day = -3.05f)
        )
    )
)
