package com.example.audioxel.data.model.soundcloud

import com.google.gson.annotations.SerializedName

data class SoundCloudUser(
    val id: Long,
    val kind: String,
    val permalink: String,
    val username: String,
    @SerializedName("last_modified") val lastModified: String,
    val uri: String,
    @SerializedName("permalink_url") val permalinkUrl: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val country: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("full_name") val fullName: String?,
    val city: String?,
    val description: String?,
    @SerializedName("track_count") val trackCount: Int,
    @SerializedName("followers_count") val followersCount: Int,
    @SerializedName("followings_count") val followingsCount: Int,
    @SerializedName("public_favorites_count") val publicFavoritesCount: Int
)
