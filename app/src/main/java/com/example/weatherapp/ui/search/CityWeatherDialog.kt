package com.example.weatherapp.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.weatherapp.ui.home.HomeScreen
import com.example.weatherapp.ui.model.CityWeatherViewData
import com.example.weatherapp.utils.getFakeCityWeatherViewData

@Composable
fun CityWeatherDialog(cityWeather: CityWeatherViewData?, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        HomeScreen(cityWeather = cityWeather, fillSize = false)
    }
}

@Preview
@Composable
fun PreviewCityWeatherDialog() {
    CityWeatherDialog(getFakeCityWeatherViewData()) {}
}
