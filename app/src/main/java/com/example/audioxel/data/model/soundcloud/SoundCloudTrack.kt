package com.example.audioxel.data.model.soundcloud

import com.google.gson.annotations.SerializedName

data class SoundCloudTrack(
    val id: Long,
    val title: String,
    val description: String?,
    val duration: Long,
    @SerializedName("genre") val genre: String?,
    @SerializedName("permalink_url") val permalinkUrl: String,
    @SerializedName("artwork_url") val artworkUrl: String?,
    @SerializedName("stream_url") val streamUrl: String?,
    @SerializedName("playback_count") val playbackCount: Int?,
    @SerializedName("likes_count") val likes_count: Int?,
    @SerializedName("user") val user: SoundCloudUser
)
