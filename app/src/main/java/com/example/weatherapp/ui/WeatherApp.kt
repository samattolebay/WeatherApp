package com.example.weatherapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.data.network.model.CityWeather
import com.example.weatherapp.ui.theme.BottomBarBackgroundColor
import com.example.weatherapp.ui.theme.BottomBarContentColor
import com.example.weatherapp.ui.theme.BottomBarShape

@Composable
fun WeatherApp(
    navController: NavHostController,
    cityWeather: State<CityWeather?>,
    cities: SnapshotStateList<CityWeather>,
    onSearch: (String) -> Unit
) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
    )
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.clip(BottomBarShape),
                backgroundColor = BottomBarBackgroundColor,
                contentColor = BottomBarContentColor
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        alwaysShowLabel = false,
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(cityWeather.value) }
            composable(Screen.Search.route) { SearchScreen(cities, onSearch) }
        }
    }
}
