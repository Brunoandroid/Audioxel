package com.example.audioxel.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.audioxel.components.SearchBar
import com.example.audioxel.components.ArtistSection
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.screens.home.sections.FeaturedSection
import com.example.audioxel.screens.home.sections.PlaylistSection
import com.example.audioxel.screens.home.sections.QuickChoicesSection
import com.example.audioxel.screens.home.sections.RecentSection
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnSurfaceVariant

@Composable
fun HomeScreen(
    onUserClick: (SoundCloudUser) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = Dimens.PaddingDefault,
            vertical = Dimens.PaddingDefault
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
    ) {
        item {
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
            )
        }

        if (uiState.searchQuery.isNotBlank()) {
            if (uiState.isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            } else if (uiState.error != null) {
                item {
                    Text(
                        text = uiState.error!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(Dimens.PaddingDefault)
                    )
                }
            } else if (uiState.searchResults.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.error_no_results_for_query, uiState.searchQuery),
                        color = OnSurfaceVariant,
                        modifier = Modifier.padding(Dimens.PaddingDefault)
                    )
                }
            } else {
                item {
                    ArtistSection(
                        artists = uiState.searchResults,
                        onArtistClick = { onUserClick(it) }
                    )
                }
            }
        } else {
            item {
                FeaturedSection(
                    items = uiState.featuredItems,
                    onItemClick = { },
                    onSeeAllClick = { }
                )
            }

            item {
                QuickChoicesSection(
                    items = uiState.quickChoices,
                    onItemClick = { },
                    onSeeAllClick = { }
                )
            }

            item {
                RecentSection(
                    items = uiState.recentItems,
                    onItemClick = { },
                    onSeeAllClick = { }
                )
            }

            item {
                PlaylistSection(
                    items = uiState.playlistItems,
                    onItemClick = { },
                    onSeeAllClick = { }
                )
            }
        }
    }
}
