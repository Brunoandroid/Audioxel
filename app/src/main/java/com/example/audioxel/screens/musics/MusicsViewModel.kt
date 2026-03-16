package com.example.audioxel.screens.musics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioxel.R
import com.example.audioxel.data.model.soundcloud.SoundCloudTrack
import com.example.audioxel.data.repository.Repository
import com.example.audioxel.util.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MusicsUiState(
    val tracks: List<SoundCloudTrack> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MusicsViewModel @Inject constructor(
    private val repository: Repository,
    private val resourceManager: ResourceManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MusicsUiState())
    val uiState: StateFlow<MusicsUiState> = _uiState.asStateFlow()

    private val userId: Long? = savedStateHandle["userId"]

    init {
        userId?.let {
            loadTracks(it)
        } ?: run {
            _uiState.update { it.copy(error = resourceManager.getString(R.string.error_user_id_not_found)) }
        }
    }

    private fun loadTracks(userId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.getUserTracks(userId)
                .onSuccess { tracks ->
                    _uiState.update { it.copy(tracks = tracks, isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }
        }
    }
}
