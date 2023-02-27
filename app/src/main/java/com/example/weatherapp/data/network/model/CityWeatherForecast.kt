package com.example.weatherapp.data.network.model

data class CityWeatherForecast(
    val city: City,
    val cnt: Int,
    val list: Array<DayWeather>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CityWeatherForecast

        if (city != other.city) return false
        if (cnt != other.cnt) return false
        if (!list.contentEquals(other.list)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + cnt
        result = 31 * result + list.contentHashCode()
        return result
    }
}
