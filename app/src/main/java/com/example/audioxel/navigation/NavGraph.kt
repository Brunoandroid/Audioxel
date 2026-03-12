package com.example.audioxel.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.audioxel.screens.explore.ExploreScreen
import com.example.audioxel.screens.home.HomeScreen
import com.example.audioxel.screens.musics.MusicsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onUserClick = { user ->
                navController.navigate(Screen.Musics.createRoute(user.id))
            })
        }
        composable(Screen.Explore.route) {
            ExploreScreen(onUserClick = { user ->
                navController.navigate(Screen.Musics.createRoute(user.id))
            })
        }
        composable(
            route = Screen.Musics.route,
            arguments = listOf(navArgument("userId") { type = NavType.LongType })
        ) {
            MusicsScreen()
        }
        composable(BottomNavItem.Library.route) {
            PlaceholderScreen("Library")
        }
        composable(BottomNavItem.Profile.route) {
            PlaceholderScreen("Profile")
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Tela de $name", color = Color.White)
    }
}
