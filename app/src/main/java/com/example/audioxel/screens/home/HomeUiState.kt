package com.example.audioxel.screens.home

import com.example.audioxel.data.model.FeaturedItem
import com.example.audioxel.data.model.PlaylistItem
import com.example.audioxel.data.model.QuickChoiceItem
import com.example.audioxel.data.model.RecentItem

data class HomeUiState(
    val featuredItems: List<FeaturedItem> = emptyList(),
    val quickChoices: List<QuickChoiceItem> = emptyList(),
    val recentItems: List<RecentItem> = emptyList(),
    val playlistItems: List<PlaylistItem> = emptyList(),
    val searchQuery: String = ""
)
