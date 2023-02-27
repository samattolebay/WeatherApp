package com.example.weatherapp.data.network

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainRepositoryImpl(
    private val networkService: WeatherApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    override suspend fun fetchCityWeatherByName(city: String) {
        getResult { networkService.fetchCityWeatherByName(city) }
    }

    override suspend fun fetchCityWeatherByPoint(lat: String, lon: String) {
        getResult { networkService.fetchCityWeatherByPoint(lat, lon) }
    }

    private suspend fun <T> getResult(request: suspend () -> Response<T>) {
        try {
            val response = withContext(ioDispatcher) {
                request()
            }
            if (response.isSuccessful) {
                val body = response.body()
                Log.i(TAG, body.toString())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    companion object {
        private const val TAG = "MainRepositoryImpl"
    }
}
