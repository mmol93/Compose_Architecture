package com.example.compose_architecture.ui.screen.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import timber.log.Timber

/**
 * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#BottomAppBar(kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function0,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.PaddingValues,androidx.compose.foundation.layout.WindowInsets)
 * 디자인 가이드: https://m3.material.io/components/bottom-app-bar/guidelines
 * */
@Composable
fun ViewScreen.ShowBottomAppbarWidget() {
    val itemList = List(20) { "Text $it" }
    val lazyListState = rememberLazyListState()
    var previousVisibleItemIndex = 0

    var selectedItem by remember { mutableStateOf(0) }
    var isLastItemSelected by remember { mutableStateOf(false) }
    var showBottomAppBar by remember { mutableStateOf(true) }

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.layoutInfo.visibleItemsInfo.first().index
        }.distinctUntilChanged().collect { firstVisibleItemIndex ->
            // 위로 스크롤 중이면 bottomAppBar 표시 아래로 스크롤 중이면 비표시
            showBottomAppBar = when {
                firstVisibleItemIndex == 0 -> true
                lazyListState.layoutInfo.visibleItemsInfo.last().index == itemList.size - 1 -> true
                else -> firstVisibleItemIndex < previousVisibleItemIndex
            }

            previousVisibleItemIndex = firstVisibleItemIndex
        }
    }

    LaunchedEffect(isLastItemSelected) {
        snapshotFlow {
            isLastItemSelected
        }.distinctUntilChanged().filter { it }.collect {
            // bottomAppBar가 리스트의 아이템을 가리는 것을 막기 위해 스크롤을 강제로 이동 시킨다.
            lazyListState.scrollToItem(itemList.size - 1)
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomAppBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 500)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 500)
                )
            ) {
                BottomAppBar()
            }
        }
    ) {
        LazyColumn(state = lazyListState, modifier = Modifier.padding(it)) {
            items(count = itemList.size) { index ->
                Text(
                    text = itemList[index],
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .clickable {
                            showBottomAppBar = true
                            // 마지막 아이템을 보고 있을 때 클릭하면 bottomAppBar가 마지막 아이템을 가려버리는 것을 막는다.
                            if (lazyListState.layoutInfo.visibleItemsInfo.last().index == itemList.size - 1) {
                                isLastItemSelected = true
                            }
                        }
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }
}

@Composable
fun BottomAppBar() = androidx.compose.material3.BottomAppBar(
    actions = {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Check, contentDescription = "Localized description")
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = "Localized description",
            )
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Localized description",
            )
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Localized description",
            )
        }
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = { /* do something */ },
            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
        ) {
            Icon(Icons.Filled.Add, "Localized description")
        }
    }
)