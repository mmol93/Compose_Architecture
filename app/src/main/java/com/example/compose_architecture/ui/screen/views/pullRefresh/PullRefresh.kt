package com.example.compose_architecture.ui.screen.views.pullRefresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_architecture.ui.screen.views.ViewScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewScreen.ShowPullRefreshWidget() {
    val viewModel = viewModel<PullRefreshViewModel>()
    val state by viewModel.state
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = viewModel::loadOrders
    )

    // pullRefresh가 위에 표시되야 하는 위젯들은 반드시 같은 Box 안에 있어야 한다.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        LazyColumn {
            items(state.orders) {
                ListItem(
                    headlineContent = { Text(text = "${it.title} (€ ${"%.2f".format(it.price)})") },
                )
            }
        }

        // 반드시 모든 위젯들 보다 제일 아래에 위치해야한다.
        PullRefreshIndicator(
            refreshing = viewModel.state.value.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            // status에 따라 색을 조절할 수 있다.
            backgroundColor = if (viewModel.state.value.isLoading) Color.Red else Color.Green,
        )
    }
}