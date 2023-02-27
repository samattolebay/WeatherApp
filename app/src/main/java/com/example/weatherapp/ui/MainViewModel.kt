package com.example.weatherapp.ui

import androidx.lifecycle.*
import com.example.weatherapp.data.network.MainRepository
import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.utils.ResultOf
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _city = MutableLiveData<CityWeather>()
    val city: LiveData<CityWeather>
        get() = _city

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchCityWeather(city: String) {
        viewModelScope.launch {
            when (val response = repository.fetchCityWeatherByName(city)) {
                is ResultOf.Success -> {
                    val responseCity = response.data ?: return@launch
                    _city.value = responseCity
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
