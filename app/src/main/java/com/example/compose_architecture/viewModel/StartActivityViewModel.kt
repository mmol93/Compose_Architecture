package com.example.compose_architecture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_architecture.screen.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StartActivityViewModel : ViewModel() {
    // TODO: set temp data flowOf("success")
    val uiState: StateFlow<UiState> = flowOf("success").map {
        UiState.Success(it)
    }
        // map으로 생성된 flow 객체를 stateFlow로 변환한다.
        .stateIn(
            scope = viewModelScope,
            initialValue = UiState.Loading,
            // StateFlow가 구독 중인 동안에만 데이터를 공유하도록 지정하며 구독을 정지하더라도 5초간 해당 데이터를 유지하도록 한다.
            started = SharingStarted.WhileSubscribed(5_000),
        )
}
