package com.example.audioxel.data.repository

import com.example.audioxel.data.model.FeaturedItem
import com.example.audioxel.data.model.PlaylistItem
import com.example.audioxel.data.model.QuickChoiceItem
import com.example.audioxel.data.model.RecentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getFeaturedItems(): Flow<List<FeaturedItem>> = flowOf(
        listOf(
            FeaturedItem(1, "Blinding Lights", "The Weeknd", "Destaque do Momento"),
            FeaturedItem(2, "After Hours", "The Weeknd", "Lançamento"),
        )
    )

    override fun getQuickChoices(): Flow<List<QuickChoiceItem>> = flowOf(
        listOf(
            QuickChoiceItem(1, "Techno Classics"),
            QuickChoiceItem(2, "Pop Rising"),
            QuickChoiceItem(3, "Classic Rock"),
            QuickChoiceItem(4, "Acoustic Hits"),
        )
    )

    override fun getRecentItems(): Flow<List<RecentItem>> = flowOf(
        listOf(
            RecentItem(1, "Blinding Lights", "The Weeknd"),
            RecentItem(2, "Levitating", "Dua Lipa"),
            RecentItem(3, "Save Your Tears", "The Weeknd"),
            RecentItem(4, "Peaches", "Justin Bieber"),
            RecentItem(5, "Good 4 U", "Olivia Rodrigo"),
        )
    )

    override fun getPlaylistItems(): Flow<List<PlaylistItem>> = flowOf(
        listOf(
            PlaylistItem(1, "Top Hits 2024"),
            PlaylistItem(2, "Chill Vibes"),
            PlaylistItem(3, "Workout Mix"),
            PlaylistItem(4, "Lo-Fi Study"),
            PlaylistItem(5, "Party Anthems"),
        )
    )
}
