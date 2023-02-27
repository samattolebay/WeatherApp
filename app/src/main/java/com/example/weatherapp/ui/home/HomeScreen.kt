package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.model.CityWeatherViewData
import com.example.weatherapp.ui.theme.*
import com.example.weatherapp.utils.getFakeCityWeatherViewData

@Composable
fun HomeScreen(cityWeather: CityWeatherViewData?, fillSize: Boolean = true) {
    if (cityWeather == null) return
    Column(
        modifier = (if (fillSize) Modifier.fillMaxSize() else Modifier.wrapContentHeight())
            .background(color = MainBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Text(text = cityWeather.name, style = ExtraMediumTextStyle, maxLines = 1)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Text(text = cityWeather.weather[0].main, style = ExtraMediumTextStyle)
        Text(text = cityWeather.weather[0].description, style = SmallTextStyle)
        Text(
            text = stringResource(id = R.string.temperature, cityWeather.main.temp.toInt()),
            style = ExtraBigTextStyle
        )
        Spacer(
            modifier = if (fillSize) Modifier.weight(1f) else Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_feels_like),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = stringResource(
                        id = R.string.feels_like,
                        cityWeather.main.feelsLike.toInt()
                    ), style = DefaultTextStyle
                )
                Text(text = stringResource(id = R.string.feels_like_text), style = DefaultTextStyle)
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = stringResource(id = R.string.humidity, cityWeather.main.humidity),
                    style = DefaultTextStyle
                )
                Text(text = stringResource(id = R.string.humidity_text), style = DefaultTextStyle)
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_wind_speed),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = stringResource(id = R.string.wind, cityWeather.wind.speed),
                    style = DefaultTextStyle
                )
                Text(text = stringResource(id = R.string.wind_text), style = DefaultTextStyle)
            }
        }
        Text(text = stringResource(id = R.string.forecast), style = MediumTextStyle)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(cityWeather.forecast) {
                MiniDayForecast(it)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }

}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(getFakeCityWeatherViewData())
}
