package com.example.audioxel.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioxel.BuildConfig
import com.example.audioxel.data.repository.Repository
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
    val authError: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val audioRepository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        authenticateSoundCloud()
    }

    private fun authenticateSoundCloud() {
        if (BuildConfig.SOUNDCLOUD_CLIENT_ID == "SEU_CLIENT_ID" || BuildConfig.SOUNDCLOUD_CLIENT_ID.isEmpty()) {
            _uiState.update { it.copy(authError = "Client ID não configurado no gradle.properties") }
            return
        }

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
                        authError = "Falha na autenticação: ${error.message}"
                    ) 
                }
            }
        }
    }
}
