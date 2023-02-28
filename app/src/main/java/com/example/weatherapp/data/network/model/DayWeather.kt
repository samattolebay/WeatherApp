package com.example.weatherapp.data.network.model

data class DayWeather(
    val weather: Array<Weather>,
    val temp: Temp
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DayWeather

        if (!weather.contentEquals(other.weather)) return false
        if (temp != other.temp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = weather.contentHashCode()
        result = 31 * result + temp.hashCode()
        return result
    }
}
