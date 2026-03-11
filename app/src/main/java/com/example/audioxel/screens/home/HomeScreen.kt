package com.example.audioxel.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioxel.components.SearchBar
import com.example.audioxel.screens.home.sections.FeaturedSection
import com.example.audioxel.screens.home.sections.PlaylistSection
import com.example.audioxel.screens.home.sections.QuickChoicesSection
import com.example.audioxel.screens.home.sections.RecentSection
import com.example.audioxel.ui.theme.Dimens

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

        item {
            FeaturedSection(
                items = uiState.featuredItems,
                onItemClick = { /* Navegar para detalhe */ },
                onSeeAllClick = { /* Ver todos destaques */ }
            )
        }

        item {
            QuickChoicesSection(
                items = uiState.quickChoices,
                onItemClick = { /* Navegar para detalhe */ },
                onSeeAllClick = { /* Ver todas escolhas */ }
            )
        }

        item {
            RecentSection(
                items = uiState.recentItems,
                onItemClick = { /* Navegar para detalhe */ },
                onSeeAllClick = { /* Ver todos recentes */ }
            )
        }

        item {
            PlaylistSection(
                items = uiState.playlistItems,
                onItemClick = { /* Navegar para detalhe */ },
                onSeeAllClick = { /* Ver todas playlists */ }
            )
        }
    }
}
