package com.example.audioxel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.audioxel.audio.AudioPlayer
import com.example.audioxel.components.AudioxelAppBar
import com.example.audioxel.components.BottomNavBar
import com.example.audioxel.components.player.MiniPlayer
import com.example.audioxel.navigation.SetupNavGraph
import com.example.audioxel.ui.theme.Background
import javax.inject.Inject

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
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
            Column {
                if (uiState.authError != null) {
                    Snackbar {
                        Text(uiState.authError!!)
                    }
                }
                
                val track = uiState.currentTrack
                MiniPlayer(
                    trackName = track.title,
                    artistName = track.user.username,
                    artworkUrl = track.artworkUrl ?: track.user.avatarUrl,
                    isPlaying = uiState.isPlaying,
                    onPlayPauseClick = { viewModel.togglePlayPause() },
                    onNextClick = { }
                )
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
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SetupNavGraph(navController = navController)
        }
    }
}
