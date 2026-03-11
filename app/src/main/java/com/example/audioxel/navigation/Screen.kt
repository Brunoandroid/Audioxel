package com.example.audioxel.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(route = Routes.HOME)
    data object Explore : Screen(route = Routes.EXPLORE)
    data object Library : Screen(route = Routes.LIBRARY)
    data object Profile : Screen(route = Routes.PROFILE)
}