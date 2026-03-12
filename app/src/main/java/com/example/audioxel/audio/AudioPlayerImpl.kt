package com.example.audioxel.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class AudioPlayerImpl @Inject constructor(
    context: Context
) : AudioPlayer {

    override val player: Player = ExoPlayer.Builder(context).build()

    override fun play(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun resume() {
        player.play()
    }

    override fun stop() {
        player.stop()
    }

    override fun seekTo(position: Long) {
        player.seekTo(position)
    }

    override fun release() {
        player.release()
    }
}
