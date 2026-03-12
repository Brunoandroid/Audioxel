package com.example.audioxel.data.remote

import com.example.audioxel.data.model.soundcloud.SoundCloudTokenResponse
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST(ApiEndpoints.OAUTH_TOKEN)
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<SoundCloudTokenResponse>

    @GET(ApiEndpoints.USERS)
    suspend fun searchUsers(
        @Query("q") query: String
    ): Response<List<SoundCloudUser>>
}