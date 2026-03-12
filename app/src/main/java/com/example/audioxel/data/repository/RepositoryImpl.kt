package com.example.audioxel.data.repository

import com.example.audioxel.data.model.home.FeaturedItem
import com.example.audioxel.data.model.home.PlaylistItem
import com.example.audioxel.data.model.home.QuickChoiceItem
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.data.model.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.data.remote.ApiService
import com.example.audioxel.data.security.ISecureTokenStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val tokenStore: ISecureTokenStore
) : Repository {

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

    override suspend fun fetchAccessToken(clientId: String, clientSecret: String): Result<SoundCloudTokenResponse> {
        return try {
            val response = api.getAccessToken(clientId = clientId, clientSecret = clientSecret)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    tokenStore.saveAccessToken(body.accessToken)
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Error fetching token: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchArtists(query: String): Result<List<SoundCloudUser>> {
        if (tokenStore.getAccessToken() == null) return Result.failure(Exception("No access token available"))
        return try {
            val response = api.searchUsers(query = query)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Error searching artists: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
