package com.example.weatherapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.network.model.*
import com.example.weatherapp.ui.theme.BigTextStyle
import com.example.weatherapp.ui.theme.MediumTextStyle
import com.example.weatherapp.ui.theme.SmallTextStyle
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DefaultTextStyle

@Composable
fun HomeScreen(cityWeather: CityWeather?) {
    if (cityWeather == null) return
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Text(text = cityWeather.name, style = MediumTextStyle, maxLines = 1)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Text(text = cityWeather.weather[0].main, style = MediumTextStyle)
        Text(text = cityWeather.weather[0].description, style = SmallTextStyle)
        Text(
            text = stringResource(id = R.string.temperature, cityWeather.main.temp.toInt()),
            style = BigTextStyle
        )
        Spacer(modifier = Modifier.weight(1f))
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
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
    }

}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        CityWeather(
            coord = Coordinate(lon = 76.95, lat = 43.25),
            weather = arrayOf(
                Weather(
                    id = 803,
                    cityName = "null",
                    main = "Clouds",
                    description = "nublado",
                    icon = "04"
                )
            ),
            sys = Sys(country = "KZ"),
            main = Main(temp = -3.05f, feelsLike = 0.0f, humidity = 80),
            wind = Wind(speed = 1.0f),
            name = "Almaty",
            photoReference = null
        )
    )
}
