package com.example.audioxel.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioxel.data.model.home.FeaturedItem
import com.example.audioxel.data.model.home.PlaylistItem
import com.example.audioxel.data.model.home.QuickChoiceItem
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.data.model.soundcloud.SoundCloudUser
import com.example.audioxel.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val featuredItems: List<FeaturedItem> = emptyList(),
    val quickChoices: List<QuickChoiceItem> = emptyList(),
    val recentItems: List<RecentItem> = emptyList(),
    val playlistItems: List<PlaylistItem> = emptyList(),
    val searchQuery: String = "",
    val searchResults: List<SoundCloudUser> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val audioRepository: Repository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _searchResults = MutableStateFlow<HomeSearchState>(HomeSearchState.Idle)

    val uiState: StateFlow<HomeUiState> = combine(
        listOf(
            audioRepository.getFeaturedItems(),
            audioRepository.getQuickChoices(),
            audioRepository.getRecentItems(),
            audioRepository.getPlaylistItems(),
            _searchQuery,
            _searchResults
        )
    ) { arrayOfValues ->
        val featured = arrayOfValues[0] as List<FeaturedItem>
        val quick = arrayOfValues[1] as List<QuickChoiceItem>
        val recent = arrayOfValues[2] as List<RecentItem>
        val playlists = arrayOfValues[3] as List<PlaylistItem>
        val query = arrayOfValues[4] as String
        val searchState = arrayOfValues[5] as HomeSearchState

        HomeUiState(
            featuredItems = featured,
            quickChoices = quick,
            recentItems = recent,
            playlistItems = playlists,
            searchQuery = query,
            searchResults = (searchState as? HomeSearchState.Success)?.results ?: emptyList(),
            isLoading = searchState is HomeSearchState.Loading,
            error = (searchState as? HomeSearchState.Error)?.message
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    init {
        setupSearchDebounce()
    }

    @OptIn(FlowPreview::class)
    private fun setupSearchDebounce() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500L)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }
    }

    private suspend fun performSearch(query: String) {
        _searchResults.value = HomeSearchState.Loading
        val result = audioRepository.searchArtists(query)
        result.fold(
            onSuccess = { users ->
                _searchResults.value = HomeSearchState.Success(users)
            },
            onFailure = { error ->
                _searchResults.value = HomeSearchState.Error(error.message ?: "Erro desconhecido")
            }
        )
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.update { newQuery }
        if (newQuery.isBlank()) {
            _searchResults.value = HomeSearchState.Idle
        }
    }
}

sealed class HomeSearchState {
    data object Idle : HomeSearchState()
    data object Loading : HomeSearchState()
    data class Success(val results: List<SoundCloudUser>) : HomeSearchState()
    data class Error(val message: String) : HomeSearchState()
}
