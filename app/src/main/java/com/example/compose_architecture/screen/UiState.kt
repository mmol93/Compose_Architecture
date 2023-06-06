package com.example.compose_architecture.screen

sealed interface UiState {
    object Loading : UiState
    data class Success(val data: String?) : UiState
}
