package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ShowAlertDialogWidget(openAlertDialog: MutableState<Boolean>) {
    // 메인 아이콘 하나를 갖고 있는 AlertDialog
    if (openAlertDialog.value) {
        AlertDialog(
            // 사용자가 dialog 밖을 눌렀거나 뒤로가기 버튼을 눌렀을 경우 아래 내용이 실시된다.
            onDismissRequest = {
                openAlertDialog.value = false
            },
            // dialog의 윗 부분에 삽입될 Icon
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            // dialog의 타이틀
            title = {
                Text(text = "Title")
            },
            // dialog의 텍스트
            text = {
                Text(
                    "This area typically contains the supportive text " +
                            "which presents the details regarding the Dialog's purpose."
                )
            },
            // 확인 버튼(오른쪽에 위치)
            confirmButton = {
                TextButton(
                    onClick = {
                        openAlertDialog.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            // 가부 버튼(왼쪽에 위치)
            dismissButton = {
                TextButton(
                    onClick = {
                        openAlertDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDateDialogWidget(openDateDialog: MutableState<Boolean>) {
    // ------------- DateDialog -------------------
    // 클릭하여 받은 날짜 데이터를 표시하기 위해 스낵바 사용
    val snackStateForDateDialog = remember { SnackbarHostState() }
    val snackScopeForDateDialog = rememberCoroutineScope()
    SnackbarHost(hostState = snackStateForDateDialog, Modifier)

    // 날짜를 하나 선택할 수 있는 DatePickerDialog
    if (openDateDialog.value) {
        val datePickerState = rememberDatePickerState()
        // derivedStateOf을 사용해서 { } 안의 값이 변할 때마다 추적하고 UI를 바뀌게(remember) 하기.
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            // 사용자가 dialog 밖을 눌렀거나 뒤로가기 버튼을 눌렀을 경우 아래 내용이 실시된다.
            onDismissRequest = {
                openDateDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDateDialog.value = false
                        snackScopeForDateDialog.launch {
                            snackStateForDateDialog.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    },
                    enabled = confirmEnabled
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDateDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePickerDialogWidget(openDateRangeDialog: MutableState<Boolean>) {
    // ------------- DateRangePicker -------------------
    val snackStateForDateRangeDialog = remember { SnackbarHostState() }
    val snackScopeForDateRangeDialog = rememberCoroutineScope()
    SnackbarHost(hostState = snackStateForDateRangeDialog, Modifier)

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val dateRangePickerState = rememberDateRangePickerState()

    if (openDateRangeDialog.value) {
        // DateRangePicker는 Dialog가 아니기 때문에 dialog 처럼 보이게 하기 위해 BottomSheet 안에 넣어서 정의하는 것이 좋다.
        ModalBottomSheet(
            // BottomSheet가 dismiss되 었을 때 어떤 행동을 할지 정의
            onDismissRequest = { openDateRangeDialog.value = false },
            // BottomSheet의 열고 닫기 상태 정의
            sheetState = bottomSheetState,
        ) {
            // 아래의 Column에 들어있는 모든 widget이 DateRangePicker에서 사용될 widget들이다.
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
                // Add a row with "Save" and dismiss actions.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { openDateRangeDialog.value = false }) {
                        Icon(Icons.Filled.Close, contentDescription = "Localized description")
                    }
                    TextButton(
                        onClick = {
                            openDateRangeDialog.value = false
                            snackScopeForDateRangeDialog.launch {
                                snackStateForDateRangeDialog.showSnackbar(
                                    "Saved range (timestamps): " + "${dateRangePickerState.selectedStartDateMillis!!..dateRangePickerState.selectedEndDateMillis!!}"
                                )
                            }
                        },
                        enabled = dateRangePickerState.selectedEndDateMillis != null
                    ) {
                        Text(text = "Save")
                    }
                }
                // DateRangePicker는 하나의 Widget이기 때문에 DateDialog 처럼 Dialog 형태로 출력되지 않는다.
                DateRangePicker(
                    state = dateRangePickerState,
                    modifier = Modifier.weight(1f),
                    // showModeToggle를 true로 할 경우 사용자가 직접 입력으로 설정할 수 있게된다.
                    showModeToggle = true
                )
            }
        }
    }
}