package com.example.audioxel.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.audioxel.R

sealed class BottomNavItem(
    val route: String,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object Home : BottomNavItem(
        route = "home",
        labelRes = R.string.nav_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    )

    data object Explore : BottomNavItem(
        route = "explore",
        labelRes = R.string.nav_explore,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
    )

    data object Library : BottomNavItem(
        route = "library",
        labelRes = R.string.nav_library,
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Filled.List,
    )

    data object Profile : BottomNavItem(
        route = "profile",
        labelRes = R.string.nav_profile,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    )
}