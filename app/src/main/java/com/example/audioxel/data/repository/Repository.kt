package com.example.audioxel.data.repository

import com.example.audioxel.data.model.home.FeaturedItem
import com.example.audioxel.data.model.home.PlaylistItem
import com.example.audioxel.data.model.home.QuickChoiceItem
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.data.model.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getFeaturedItems(): Flow<List<FeaturedItem>>
    fun getQuickChoices(): Flow<List<QuickChoiceItem>>
    fun getRecentItems(): Flow<List<RecentItem>>
    fun getPlaylistItems(): Flow<List<PlaylistItem>>

    suspend fun fetchAccessToken(clientId: String, clientSecret: String): Result<SoundCloudTokenResponse>
    suspend fun searchArtists(query: String): Result<List<SoundCloudUser>>
}
