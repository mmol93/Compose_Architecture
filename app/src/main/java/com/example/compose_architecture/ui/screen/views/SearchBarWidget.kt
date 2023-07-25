package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * 디자인 가이드: https://m3.material.io/components/search/overview
 * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SearchBar(kotlin.String,kotlin.Function1,kotlin.Function1,kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Function0,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.material3.SearchBarColors,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.WindowInsets,androidx.compose.foundation.interaction.MutableInteractionSource,kotlin.Function1)
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen.ShowSearchBarWidget() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val list = List(100) { "Text $it" }

    // searchBar에 입력한 query에 따른 결과를 재정렬해서 보여준다.
    @Composable
    fun arrangeList(query: String) {
        if (query.isBlank()) {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 72.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = list.size) {
                    Text(
                        list[it],
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        } else {
            val newList = list.filter { it.contains(query) }
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 72.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = newList.size) {
                    Text(
                        newList[it],
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        // Talkback focus order sorts based on x and y position before considering z-index. The
        // extra Box with semantics and fillMaxWidth is a workaround to get the search bar to focus
        // before the content.
        Box(
            Modifier
                .semantics {
                    @Suppress("DEPRECATION")
                    isContainer = true
                }
                .zIndex(1f)
                .fillMaxWidth()) {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                },
                // 일반적인 상태일 때 searchBar에 표시되는 텍스트
                placeholder = { Text("Hinted search text") },
                // 일반적인 상태일 때 searchBar의 왼쪽에 표시되는 아이콘
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                // 일반적인 상태일 때 searchBar의 오른쪽에 표시되는 아이콘
                trailingIcon = {
                    if (text.isBlank()) Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null
                    ) else Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        Modifier.clickable { text = "" }
                    )
                },
            ) {
                // SearchBar를 클릭하면 아래의 내용이 출력된다.
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(4) { idx ->
                        val resultText = "Text $idx"
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            modifier = Modifier.clickable {
                                text = resultText
                                // 클릭 이후 searchBar를 비활성화한다.
                                active = false
                            }
                        )
                    }
                }
            }
        }
        arrangeList(query = text)
    }
}