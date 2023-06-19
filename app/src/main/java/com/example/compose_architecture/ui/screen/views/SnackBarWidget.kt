package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// 예제에서는 SnackbarVisuals을 사용해 UI에서 에러가 발생할 경우 다른 message를 스낵바에 띄웠지만 여기선 SnackbarVisuals를 생략했다.
@Composable
fun ViewScreen.ShowSnackBarWidget(openSnackBar: MutableState<Boolean>) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (openSnackBar.value) {
        Scaffold(
            snackbarHost = {
                // snackBar 상태를 관리한다.
                SnackbarHost(snackBarHostState) { snackBarData ->
                    // Snack를 만든다.
                    Snackbar(
                        modifier = Modifier
                            .border(2.dp, MaterialTheme.colorScheme.secondary)
                            .padding(12.dp),
                        action = {
                            TextButton(
                                onClick = {
                                    openSnackBar.value = false
                                    println("test: ${snackBarData.visuals.message}")
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = MaterialTheme.colorScheme.inversePrimary
                                )
                            ) {
                                Text(text = "Action")
                            }
                        }
                    ) {
                        Text(text = snackBarData.visuals.message)
                    }
                }
            },
            floatingActionButton = {
                var clickCount by remember { mutableStateOf(0) }
                ExtendedFloatingActionButton(
                    onClick = {
                        // 스낵바를 보여줄 때는 비동기로 실시된다.
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                "Snackbar # ${++clickCount}"
                            )
                        }
                    }
                ) { Text("Show snackBar") }
            },
            content = { innerPadding ->
                Text(
                    text = "Body content Body content Body content Body content Body content Body content",
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
        )
    }
}