package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.data.network.MainRepository
import com.example.weatherapp.data.network.MainRepositoryImpl
import com.example.weatherapp.data.network.WeatherApiService
import kotlinx.coroutines.Dispatchers

class MyApplication : Application() {

    lateinit var repository: MainRepository

    override fun onCreate() {
        super.onCreate()
        val networkService = WeatherApiService.service
        val ioDispatcher = Dispatchers.IO
        repository = MainRepositoryImpl(networkService, ioDispatcher)
    }
}
