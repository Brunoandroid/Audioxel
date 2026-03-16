package com.example.audioxel.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioxel.BuildConfig
import com.example.audioxel.R
import com.example.audioxel.audio.AudioPlayer
import com.example.audioxel.data.model.soundcloud.SoundCloudTrack
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.data.repository.Repository
import com.example.audioxel.util.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isAuthenticating: Boolean = false,
    val authError: String? = null,
    val currentTrack: SoundCloudTrack = SoundCloudTrack(
        id = 1,
        title = "Blinding Lights",
        description = null,
        duration = 0,
        genre = null,
        permalinkUrl = "",
        artworkUrl = null,
        streamUrl = null,
        playbackCount = 0,
        likesCount = 0,
        user = SoundCloudUser(
            id = 0,
            kind = "",
            permalink = "",
            username = "The Weeknd",
            lastModified = "",
            uri = "",
            permalinkUrl = "",
            avatarUrl = "",
            country = null,
            firstName = null,
            lastName = null,
            fullName = null,
            city = null,
            description = null,
            trackCount = 0,
            followersCount = 0,
            followingsCount = 0,
            publicFavoritesCount = 0,
            playlistCount = 0
        )
    ),
    val isPlaying: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val audioRepository: Repository,
    private val audioPlayer: AudioPlayer,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        authenticateSoundCloud()
    }

    fun playTrack(track: SoundCloudTrack) {
        _uiState.update { it.copy(currentTrack = track, isPlaying = true) }
        audioPlayer.play(track)
    }

    fun togglePlayPause() {
        val nextIsPlaying = !uiState.value.isPlaying
        _uiState.update { it.copy(isPlaying = nextIsPlaying) }
        
        if (nextIsPlaying) {
            audioPlayer.resume()
        } else {
            audioPlayer.pause()
        }
    }

    private fun authenticateSoundCloud() {
        viewModelScope.launch {
            _uiState.update { it.copy(isAuthenticating = true, authError = null) }
            
            val result = audioRepository.fetchAccessToken(
                clientId = BuildConfig.SOUNDCLOUD_CLIENT_ID,
                clientSecret = BuildConfig.SOUNDCLOUD_CLIENT_SECRET
            )
            
            result.onSuccess {
                _uiState.update { it.copy(isAuthenticated = true, isAuthenticating = false) }
            }.onFailure { error ->
                _uiState.update { 
                    it.copy(
                        isAuthenticated = false, 
                        isAuthenticating = false, 
                        authError = resourceManager.getString(R.string.error_auth_failed, error.message ?: "")
                    ) 
                }
            }
        }
    }
}
