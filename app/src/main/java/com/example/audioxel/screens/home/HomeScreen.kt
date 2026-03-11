package com.example.audioxel.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.audioxel.R
import com.example.audioxel.components.FeaturedCard
import com.example.audioxel.components.PlaylistCard
import com.example.audioxel.components.QuickChoiceCard
import com.example.audioxel.components.RecentCard
import com.example.audioxel.components.SearchBar
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.ui.theme.Dimens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.PaddingDefault),
    ) {
        Spacer(modifier = Modifier.height(Dimens.PaddingDefault))

        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchQueryChange,
        )

        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

        SectionHeader(title = stringResource(R.string.section_destaques))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
            contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
        ) {
            items(uiState.featuredItems, key = { it.id }) { item ->
                FeaturedCard(item = item)
            }
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

        SectionHeader(
            title = stringResource(R.string.section_escolhas_rapidas),
            onSeeAllClick = {}
        )

        uiState.quickChoices.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.PaddingDefault),
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)
            ) {
                rowItems.forEach { item ->
                    QuickChoiceCard(
                        item = item,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            SectionHeader(title = stringResource(R.string.section_recents))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
                contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
            ) {
                items(uiState.recentItems, key = { it.id }) { item ->
                    RecentCard(item = item)
                }
            }

            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            SectionHeader(title = stringResource(R.string.section_playlists))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
                contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
            ) {
                items(uiState.playlistItems, key = { it.id }) { item ->
                    PlaylistCard(item = item)
                }
            }

            Spacer(modifier = Modifier.height(Dimens.PaddingDefault))
        }
}
