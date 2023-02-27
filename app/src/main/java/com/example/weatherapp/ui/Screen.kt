package com.example.weatherapp.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : Screen(Route.HOME.route, R.string.home, Icons.Outlined.Home)
    object Search : Screen(Route.SEARCH.route, R.string.search, Icons.Outlined.Search)
}

enum class Route(val route: String) {
    HOME("home"), SEARCH("search")
}
