package com.example.weatherapp.ui.model

import com.example.weatherapp.data.network.model.*

data class CityWeatherViewData(
    val coord: Coordinate,
    val weather: Array<Weather>,
    val sys: Sys,
    val main: Main,
    val wind: Wind,
    val name: String,
    val forecast: Array<DayWeather>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CityWeatherViewData

        if (coord != other.coord) return false
        if (!weather.contentEquals(other.weather)) return false
        if (sys != other.sys) return false
        if (main != other.main) return false
        if (wind != other.wind) return false
        if (name != other.name) return false
        if (!forecast.contentEquals(other.forecast)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coord.hashCode()
        result = 31 * result + weather.contentHashCode()
        result = 31 * result + sys.hashCode()
        result = 31 * result + main.hashCode()
        result = 31 * result + wind.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + forecast.contentHashCode()
        return result
    }

    companion object {
        fun from(cityWeather: CityWeather, forecast: Array<DayWeather>): CityWeatherViewData {
            return CityWeatherViewData(
                cityWeather.coord,
                cityWeather.weather,
                cityWeather.sys,
                cityWeather.main,
                cityWeather.wind,
                cityWeather.name,
                forecast
            )
        }
    }
}
