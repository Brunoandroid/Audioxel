package com.example.audioxel.data.repository.soundcloud

import com.example.audioxel.data.remote.soundcloud.SoundCloudApi
import com.example.audioxel.data.remote.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.remote.soundcloud.SoundCloudUser
import javax.inject.Inject

class SoundCloudRepositoryImpl @Inject constructor(
    private val api: SoundCloudApi
) : SoundCloudRepository {
    private var accessToken: String? = null

    override suspend fun fetchAccessToken(clientId: String, clientSecret: String): Result<SoundCloudTokenResponse> {
        return try {
            val response = api.getAccessToken(clientId = clientId, clientSecret = clientSecret)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    accessToken = body.accessToken
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
        val token = accessToken ?: return Result.failure(Exception("No access token available"))
        return try {
            val response = api.searchUsers(authorization = "OAuth $token", query = query)
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
