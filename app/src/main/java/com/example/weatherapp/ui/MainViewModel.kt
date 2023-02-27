package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.example.weatherapp.data.MainRepository
import com.example.weatherapp.ui.model.CityWeatherViewData
import com.example.weatherapp.utils.ResultOf
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _homeCity = MutableLiveData<CityWeatherViewData>()
    val homeCity: LiveData<CityWeatherViewData>
        get() = _homeCity

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _cities = mutableStateListOf<CityWeatherViewData>()
    val cities: SnapshotStateList<CityWeatherViewData>
        get() = _cities

    fun fetchHomeCityWeather(city: String) {
        fetchCityWeather(city, true)
    }

    fun fetchCityWeather(city: String) {
        fetchCityWeather(city, false)
    }

    private fun fetchCityWeather(city: String, isHome: Boolean) {
        viewModelScope.launch {
            when (val response = repository.fetchCityWeatherByName(city)) {
                is ResultOf.Success -> {
                    val responseCity = response.data ?: return@launch
                    val cityWeatherForecastResponse = repository.fetchCityWeatherForecastByPoint(
                        responseCity.coord.lat.toString(),
                        responseCity.coord.lon.toString()
                    )

                    Log.d("MainViewModel", "${cityWeatherForecastResponse.message} and ${cityWeatherForecastResponse.data}")

                    val data = when (cityWeatherForecastResponse) {
                        is ResultOf.Success -> {
                            val forecasts = cityWeatherForecastResponse.data ?: return@launch
                            CityWeatherViewData.from(responseCity, forecasts.list)
                        }
                        is ResultOf.Error -> {
                            CityWeatherViewData.from(responseCity, emptyArray())
                        }
                    }

                    if (isHome) _homeCity.value = data
                    if (!cities.contains(data)) {
                        _cities.add(data)
                    }
                }
                is ResultOf.Error -> {
                    _errorMessage.value = response.message
                }
            }
        }
    }

    companion object {
        fun provideFactory(repository: MainRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(repository) as T
                }
            }
    }
}
