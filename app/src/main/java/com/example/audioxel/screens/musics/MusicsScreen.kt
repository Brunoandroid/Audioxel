package com.example.audioxel.screens.musics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioxel.R
import com.example.audioxel.screens.musics.sections.TrackSection
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnSurfaceVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicsScreen(
    viewModel: MusicsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else if (uiState.error != null) {
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (uiState.tracks.isEmpty()) {
                Text(
                    text = stringResource(R.string.error_no_tracks),
                    color = OnSurfaceVariant,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(Dimens.PaddingDefault),
                    verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
                ) {
                    item {
                        TrackSection(
                            tracks = uiState.tracks,
                            onTrackClick = { }
                        )
                    }
                }
            }
        }
    }
}
