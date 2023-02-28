package com.example.weatherapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.data.network.model.DayWeather
import com.example.weatherapp.ui.theme.MediumTextStyle
import com.example.weatherapp.ui.theme.SmallTextStyle
import com.example.weatherapp.utils.getFakeCityWeatherViewData


@Composable
fun MiniDayForecast(weather: DayWeather, modifier: Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = weather.weather[0].main, style = SmallTextStyle)
        Text(
            text = stringResource(id = R.string.temperature, weather.temp.day.toInt()),
            style = MediumTextStyle
        )
    }
}

@Preview
@Composable
fun PreviewMiniDayForecast() {
    MiniDayForecast(
        weather = getFakeCityWeatherViewData().forecast[0],
        modifier = Modifier
    )
}
