package com.example.audioxel.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioxel.components.SearchBar
import com.example.audioxel.screens.explore.UserItem
import com.example.audioxel.screens.home.sections.FeaturedSection
import com.example.audioxel.screens.home.sections.PlaylistSection
import com.example.audioxel.screens.home.sections.QuickChoicesSection
import com.example.audioxel.screens.home.sections.RecentSection
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnSurfaceVariant

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
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
                        text = "Nenhum resultado encontrado para \"${uiState.searchQuery}\"",
                        color = OnSurfaceVariant,
                        modifier = Modifier.padding(Dimens.PaddingDefault)
                    )
                }
            } else {
                items(uiState.searchResults) { user ->
                    UserItem(user = user)
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
