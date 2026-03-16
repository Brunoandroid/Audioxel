package com.example.audioxel.audio

import com.example.audioxel.data.model.soundcloud.SoundCloudTrack

interface AudioPlayer {
    fun play(track: SoundCloudTrack)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position: Long)
    fun release()
}
