package com.example.audioxel.audio

import androidx.media3.common.Player

interface AudioPlayer {
    val player: Player
    fun play(url: String)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position: Long)
    fun release()
}
