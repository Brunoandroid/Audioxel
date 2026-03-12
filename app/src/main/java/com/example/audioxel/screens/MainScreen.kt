package com.example.audioxel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
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

    // Temporário para teste de áudio, no futuro usar um SharedViewModel
    // Para simplificar, vamos apenas simular o estado aqui por enquanto
    var isPlaying by remember { mutableStateOf(false) }

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
                    androidx.compose.material3.Snackbar {
                        androidx.compose.material3.Text(uiState.authError!!)
                    }
                }
                MiniPlayer(
                    trackName = "SoundCloud Song",
                    artistName = "SoundCloud Artist",
                    isPlaying = isPlaying,
                    onPlayPauseClick = { isPlaying = !isPlaying },
                    onNextClick = { /* Próxima */ }
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
