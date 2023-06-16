package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheetWidget() {
    // BottomSheet on/off 상태 저장용
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    // BottomSheet 상태 저장
    val bottomSheetState = rememberModalBottomSheetState(
        // skipPartiallyExpanded on일 경우 BottomSheet가 화면 전체를 차지하게 펼쳐진다(한 번 접을 수 없고 내리면 완전히 숨겨진다)
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    // BottomSheet를 보여주기 위해 필요한 View를 셋팅하기 위한 Column
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.toggleable(
                value = skipPartiallyExpanded,
                role = Role.Checkbox,
                onValueChange = { checked -> skipPartiallyExpanded = checked }
            )
        ) {
            Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
            Spacer(Modifier.width(16.dp))
            Text("BottomSheet를 화면 가득히 열기")
        }
        Button(onClick = { openBottomSheet = !openBottomSheet }) {
            Text(text = "Show Bottom Sheet")
        }
    }
    // BottomSheet 제어 - 즉, openBottomSheet가 바뀔 때마다 ModalBottomSheet 다시 그린다(?)
    if (openBottomSheet) {
        ModalBottomSheet(
            // BottomSheet가 dismiss되 었을 때 어떤 행동을 할지 정의
            onDismissRequest = { openBottomSheet = false },
            // BottomSheet의 열고 닫기 상태 정의
            sheetState = bottomSheetState,
        ) {
            // ModalBottomSheet 안에 어떤 형태(View, Layout)로 넣을지를 정의한다.
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    // 위에 있는 onDismissRequest 안에서 BottomSheet를 숨기는 처리를 하지 않고 그 밖에서도 하고싶다면 아래와 같이 ButtomSheetState를 지정해줘야한다.
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                openBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
            }
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it })
            LazyColumn {
                items(50) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    }
}