package com.example.weatherapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.model.CityWeatherViewData
import com.example.weatherapp.utils.getFakeCityWeatherViewData

@Composable
fun SearchScreen(cities: SnapshotStateList<CityWeatherViewData>, onSearch: (String) -> Unit) {
    val selectedCity = remember {
        mutableStateOf<CityWeatherViewData?>(null)
    }
    if (selectedCity.value != null) {
        CityWeatherDialog(selectedCity.value) {
            selectedCity.value = null
        }
    }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val text = remember {
            mutableStateOf(TextFieldValue(""))
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = { text.value = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (text.value.text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            text.value = TextFieldValue("")
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(text.value.text)
                focusManager.clearFocus()
                text.value = TextFieldValue("")
            }),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.White
            )
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(cities, key = { it.name }) {
                CityWeatherCard(it) {
                    selectedCity.value = it
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    val cities = remember {
        mutableStateListOf(getFakeCityWeatherViewData())
    }
    SearchScreen(cities) {}
}
