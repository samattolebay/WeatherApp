package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.data.network.model.CityWeatherForecast
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun fetchCityWeatherByName(
        @Query("q") city: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY,
        @Query("lang") lang: String = LANG
    ): Response<CityWeather>

    @GET("weather")
    suspend fun fetchCityWeatherByPoint(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY,
        @Query("lang") lang: String = LANG
    ): Response<CityWeather>

    @GET("forecast/daily")
    suspend fun fetchCityWeatherForecastByPoint(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("cnt") cnt: Int = CNT,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY,
        @Query("lang") lang: String = LANG
    ): Response<CityWeatherForecast>

    companion object {
        val service: WeatherApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(WeatherApiService::class.java)
        }

        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "8fae5076fce4b9ab57b9f8ed4f7eaf67"
        private const val UNITS = "metric"
        private const val LANG = "pt_br"
        private const val CNT = 3
    }
}
