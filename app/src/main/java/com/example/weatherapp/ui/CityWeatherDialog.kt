package com.example.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.utils.getFakeCityWeather

@Composable
fun CityWeatherDialog(cityWeather: CityWeather?, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        HomeScreen(cityWeather = cityWeather, fillSize = false)
    }
}

@Preview
@Composable
fun PreviewCityWeatherDialog() {
    CityWeatherDialog(getFakeCityWeather()) {}
}
