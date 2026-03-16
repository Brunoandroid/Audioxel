package com.example.audioxel.data.repository

import com.example.audioxel.R
import com.example.audioxel.data.model.home.FeaturedItem
import com.example.audioxel.data.model.home.PlaylistItem
import com.example.audioxel.data.model.home.QuickChoiceItem
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.data.model.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.model.soundcloud.SoundCloudTrack
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.data.remote.ApiService
import com.example.audioxel.data.security.ISecureTokenStore
import com.example.audioxel.util.ResourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val tokenStore: ISecureTokenStore,
    private val resourceManager: ResourceManager
) : Repository {

    override fun getFeaturedItems(): Flow<List<FeaturedItem>> = flowOf(
        listOf(
            FeaturedItem(1, resourceManager.getString(R.string.track_blinding_lights), resourceManager.getString(R.string.artist_the_weeknd), resourceManager.getString(R.string.category_destaque_momento)),
            FeaturedItem(2, resourceManager.getString(R.string.track_after_hours), resourceManager.getString(R.string.artist_the_weeknd), resourceManager.getString(R.string.category_lancamento)),
        )
    )

    override fun getQuickChoices(): Flow<List<QuickChoiceItem>> = flowOf(
        listOf(
            QuickChoiceItem(1, resourceManager.getString(R.string.choice_techno_classics)),
            QuickChoiceItem(2, resourceManager.getString(R.string.choice_pop_rising)),
            QuickChoiceItem(3, resourceManager.getString(R.string.choice_classic_rock)),
            QuickChoiceItem(4, resourceManager.getString(R.string.choice_acoustic_hits)),
        )
    )

    override fun getRecentItems(): Flow<List<RecentItem>> = flowOf(
        listOf(
            RecentItem(1, resourceManager.getString(R.string.track_blinding_lights), resourceManager.getString(R.string.artist_the_weeknd)),
            RecentItem(2, resourceManager.getString(R.string.track_levitating), resourceManager.getString(R.string.artist_dua_lipa)),
            RecentItem(3, resourceManager.getString(R.string.track_save_your_tears), resourceManager.getString(R.string.artist_the_weeknd)),
            RecentItem(4, resourceManager.getString(R.string.track_peaches), resourceManager.getString(R.string.artist_justin_bieber)),
            RecentItem(5, resourceManager.getString(R.string.track_good_4_u), resourceManager.getString(R.string.artist_olivia_rodrigo)),
        )
    )

    override fun getPlaylistItems(): Flow<List<PlaylistItem>> = flowOf(
        listOf(
            PlaylistItem(1, resourceManager.getString(R.string.playlist_top_hits_2024)),
            PlaylistItem(2, resourceManager.getString(R.string.playlist_chill_vibes)),
            PlaylistItem(3, resourceManager.getString(R.string.playlist_workout_mix)),
            PlaylistItem(4, resourceManager.getString(R.string.playlist_lofi_study)),
            PlaylistItem(5, resourceManager.getString(R.string.playlist_party_anthems)),
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
                    Result.failure(Exception(resourceManager.getString(R.string.error_body_null)))
                }
            } else {
                Result.failure(Exception(resourceManager.getString(R.string.error_fetching_token, response.code(), response.message())))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchArtists(query: String): Result<List<SoundCloudUser>> {
        if (tokenStore.getAccessToken() == null) return Result.failure(Exception(resourceManager.getString(R.string.error_no_access_token)))
        return try {
            val response = api.searchUsers(query = query)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val sortedList = body.sortedByDescending { it.playlistCount }
                    Result.success(sortedList)
                } else {
                    Result.failure(Exception(resourceManager.getString(R.string.error_body_null)))
                }
            } else {
                Result.failure(Exception(resourceManager.getString(R.string.error_searching_artists, response.code(), response.message())))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserTracks(userId: Long): Result<List<SoundCloudTrack>> {
        if (tokenStore.getAccessToken() == null) return Result.failure(Exception(resourceManager.getString(R.string.error_no_access_token)))
        return try {
            val response = api.getUserTracks(userId = userId)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception(resourceManager.getString(R.string.error_body_null)))
                }
            } else {
                Result.failure(Exception(resourceManager.getString(R.string.error_fetching_tracks, response.code(), response.message())))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
