package com.example.audioxel.data.repository.soundcloud

import com.example.audioxel.data.remote.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.remote.soundcloud.SoundCloudUser

interface SoundCloudRepository {
    suspend fun fetchAccessToken(clientId: String, clientSecret: String): Result<SoundCloudTokenResponse>
    suspend fun searchArtists(query: String): Result<List<SoundCloudUser>>
}
