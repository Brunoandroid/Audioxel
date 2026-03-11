package com.example.audioxel.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.audioxel.components.BottomNavBar
import com.example.audioxel.components.AudioxelAppBar
import com.example.audioxel.navigation.SetupNavGraph
import com.example.audioxel.ui.theme.Background

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = Background,
        topBar = {
            AudioxelAppBar(
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavBar(
                currentRoute = currentDestination?.route ?: "",
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SetupNavGraph(navController = navController)
        }
    }
}
