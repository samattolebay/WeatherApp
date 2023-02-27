package com.example.weatherapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.model.CityWeatherViewData
import com.example.weatherapp.ui.theme.*
import com.example.weatherapp.utils.getFakeCityWeatherViewData

@Composable
fun CityWeatherCard(cityWeather: CityWeatherViewData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(color = MainBackgroundColor)
            .border(1.dp, color = CardBorderColor)
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = cityWeather.sys.country, style = SmallTextStyle)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Text(
                text = cityWeather.name,
                style = MediumTextStyle.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Text(text = cityWeather.weather[0].main, style = SmallTextStyle)
        }
        Text(
            text = stringResource(id = R.string.temperature, cityWeather.main.temp.toInt()),
            style = ExtraMediumTextStyle
        )
    }
}

@Preview
@Composable
fun PreviewCityWeatherCard() {
    CityWeatherCard(cityWeather = getFakeCityWeatherViewData()) {}
}
