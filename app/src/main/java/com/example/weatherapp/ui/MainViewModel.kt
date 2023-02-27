package com.example.weatherapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.example.weatherapp.data.MainRepository
import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.utils.ResultOf
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _homeCity = MutableLiveData<CityWeather>()
    val homeCity: LiveData<CityWeather>
        get() = _homeCity

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _cities = mutableStateListOf<CityWeather>()
    val cities: SnapshotStateList<CityWeather>
        get() = _cities

    fun fetchHomeCityWeather(city: String) {
        viewModelScope.launch {
            when (val response = repository.fetchCityWeatherByName(city)) {
                is ResultOf.Success -> {
                    val responseCity = response.data ?: return@launch
                    _homeCity.value = responseCity

                    if (!cities.contains(responseCity))
                        _cities.add(responseCity)
                }
                is ResultOf.Error -> {
                    _errorMessage.value = response.message
                }
            }
        }
    }

    fun fetchCityWeather(city: String) {
        viewModelScope.launch {
            when (val response = repository.fetchCityWeatherByName(city)) {
                is ResultOf.Success -> {
                    val responseCity = response.data ?: return@launch
                    if (!cities.contains(responseCity))
                        _cities.add(responseCity)
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
