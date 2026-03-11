package com.example.audioxel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.components.BottomNavBar
import com.example.audioxel.components.PlaylistCard
import com.example.audioxel.components.RecentCard
import com.example.audioxel.components.SearchBar
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.components.TopGreetingBar
import com.example.audioxel.data.model.PlaylistItem
import com.example.audioxel.data.model.RecentItem
import com.example.audioxel.navigation.BottomNavItem
import com.example.audioxel.ui.theme.Background
import com.example.audioxel.ui.theme.Dimens

@Composable
fun HomeScreen() {
    var currentRoute by remember { mutableStateOf(BottomNavItem.Home.route) }
    var searchQuery by remember { mutableStateOf("") }

    val recentItems = remember {
        listOf(
            RecentItem(1, "Blinding Lights", "The Weeknd"),
            RecentItem(2, "Levitating", "Dua Lipa"),
            RecentItem(3, "Save Your Tears", "The Weeknd"),
            RecentItem(4, "Peaches", "Justin Bieber"),
            RecentItem(5, "Good 4 U", "Olivia Rodrigo"),
        )
    }

    val playlistItems = remember {
        listOf(
            PlaylistItem(1, "Top Hits 2024"),
            PlaylistItem(2, "Chill Vibes"),
            PlaylistItem(3, "Workout Mix"),
            PlaylistItem(4, "Lo-Fi Study"),
            PlaylistItem(5, "Party Anthems"),
        )
    }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onItemClick = { item -> currentRoute = item.route },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimens.PaddingDefault),
        ) {
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            TopGreetingBar()

            Spacer(modifier = Modifier.height(Dimens.PaddingDefault))

            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
            )

            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            SectionHeader(title = stringResource(R.string.section_recents))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
                contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
            ) {
                items(recentItems, key = { it.id }) { item ->
                    RecentCard(item = item)
                }
            }

            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            SectionHeader(title = stringResource(R.string.section_playlists))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
                contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
            ) {
                items(playlistItems, key = { it.id }) { item ->
                    PlaylistCard(item = item)
                }
            }

            Spacer(modifier = Modifier.height(Dimens.PaddingDefault))
        }
    }
}
