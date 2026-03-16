package com.example.audioxel.audio

import android.util.Log
import com.example.audioxel.data.model.soundcloud.SoundCloudTrack
import javax.inject.Inject

class AudioPlayerImpl @Inject constructor() : AudioPlayer {

    private val TAG = "AudioPlayerImpl"

    override fun play(track: SoundCloudTrack) {
        Log.d(TAG, "Playing track: ${track.title}")
    }

    override fun pause() {
        Log.d(TAG, "Pausing playback")
    }

    override fun resume() {
        Log.d(TAG, "Resuming playback")
    }

    override fun stop() {
        Log.d(TAG, "Stopping playback")
    }

    override fun seekTo(position: Long) {
        Log.d(TAG, "Seeking to $position")
    }

    override fun release() {
        Log.d(TAG, "Releasing resources")
    }
}