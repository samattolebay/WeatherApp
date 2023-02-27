package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.utils.ResultOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainRepositoryImpl(
    private val networkService: WeatherApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    override suspend fun fetchCityWeatherByName(city: String): ResultOf<CityWeather> {
        return getResult { networkService.fetchCityWeatherByName(city) }
    }

    override suspend fun fetchCityWeatherByPoint(lat: String, lon: String): ResultOf<CityWeather> {
        return getResult { networkService.fetchCityWeatherByPoint(lat, lon) }
    }

    private suspend fun <T> getResult(request: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = withContext(ioDispatcher) {
                request()
            }
            if (response.isSuccessful) {
                val body = response.body() ?: return ResultOf.Error("Unknown Error!")
                Log.i(TAG, body.toString())
                ResultOf.Success(body)
            } else {
                ResultOf.Error(response.message())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            ResultOf.Error(e.message.toString())
        }
    }

    companion object {
        private const val TAG = "MainRepositoryImpl"
    }
}
