package com.example.audioxel.screens.explore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioxel.R
import com.example.audioxel.components.SearchBar
import com.example.audioxel.components.ArtistSection
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnSurfaceVariant

@Composable
fun ExploreScreen(
    onUserClick: (SoundCloudUser) -> Unit,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.PaddingDefault)
    ) {
        Spacer(modifier = Modifier.height(Dimens.PaddingDefault))
        
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchQueryChange
        )

        Spacer(modifier = Modifier.height(Dimens.PaddingDefault))

        Box(modifier = Modifier.fillMaxSize()) {
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
            } else if (uiState.results.isEmpty() && uiState.searchQuery.isNotBlank()) {
                Text(
                    text = stringResource(R.string.error_no_results),
                    color = OnSurfaceVariant,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge),
                    contentPadding = PaddingValues(bottom = Dimens.PaddingDefault)
                ) {
                    item {
                        ArtistSection(
                            artists = uiState.results,
                            onArtistClick = onUserClick
                        )
                    }
                }
            }
        }
    }
}
