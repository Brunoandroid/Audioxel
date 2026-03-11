package com.example.audioxel.data.repository

import com.example.audioxel.data.model.FeaturedItem
import com.example.audioxel.data.model.PlaylistItem
import com.example.audioxel.data.model.QuickChoiceItem
import com.example.audioxel.data.model.RecentItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getFeaturedItems(): Flow<List<FeaturedItem>>
    fun getQuickChoices(): Flow<List<QuickChoiceItem>>
    fun getRecentItems(): Flow<List<RecentItem>>
    fun getPlaylistItems(): Flow<List<PlaylistItem>>
}
