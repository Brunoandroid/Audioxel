package com.example.audioxel.data.remote.soundcloud

import retrofit2.Response
import retrofit2.http.*

interface SoundCloudApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<SoundCloudTokenResponse>

    @GET("users")
    suspend fun searchUsers(
        @Header("Authorization") authorization: String,
        @Query("q") query: String
    ): Response<List<SoundCloudUser>>

    companion object {
        const val BASE_URL = "https://api.soundcloud.com/"
    }
}
