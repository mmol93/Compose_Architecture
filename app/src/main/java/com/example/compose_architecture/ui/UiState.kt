package com.example.compose_architecture.ui

sealed interface UiState {
    object Loading : UiState
    data class Success(val data: String?) : UiState
}
