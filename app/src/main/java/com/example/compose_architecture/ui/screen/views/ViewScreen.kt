package com.example.compose_architecture.ui.screen.views

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose_architecture.model.screen.ViewScreens

object ViewScreen {
    /**
     * ViewScreen 표시
     * */
    @Composable
    fun ShowViewScreen(mainActivityNavHostController: NavHostController) {
        LazyColumn {
            items(count = ViewScreens.values().size) { index ->
                Text(
                    text = ViewScreens.values()[index].name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .clickable {
                            mainActivityNavHostController.navigate(ViewScreens.values()[index].name)
                        }
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }

    /**
     * 컴포즈의 ImageView에 대한 예시(둥근 이미지 만들기, 테두리 넣기 등)
     * 참조: https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko
     * */
    @Composable
    fun ShowImageView() {
        ShowImageWidget()
    }

    /**
     * 컴포즈의 Material2를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/jetpack/compose/layouts/material?hl=ko
     * */
    @Composable
    fun ShowDrawerM2() {
        ShowDrawerWidgetM2()
    }

    /**
     * 컴포즈의 Material3를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)
     * */
    @Composable
    fun ShowDrawerM3() {
        ShowDrawerWidgetM3()
    }

    /**
     * Material3의 Scaffold를 사용한 TopBar 만들기
     * 디자인 가이드: https://m3.material.io/components/top-app-bar/guidelines
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#CenterAlignedTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)
     * */
    @Composable
    fun ShowTopAppBar(navHostController: NavHostController) {
        ShowTopAppBarWidget(navHostController)
    }

    /**
     * Navigate시 사용되는 임시 화면
     * */
    @Composable
    fun ShowTempScreen() {
        ShowTempWidget()
    }

    /**
     * Collapsing 가능한 TopAppBar 구현
     * */
    @Composable
    fun ShowCollapsingTopAppBar() {
        ShowCollapsingTopAppBarWidget()
    }

    /**
     * 컴포즈의 TextView 및 EditTextView를 구현해본다.
     * 개발 가이드: https://developer.android.com/jetpack/compose/text?hl=ko
     * */
    @Composable
    fun ShowTextViews(context: Context) {
        ShowTextWidget(context)
    }

    /**
     * 기본적인 BottomSheet 사용 방법
     * M3에서 BottomSheet를 생성하는 방법은 두 가지가 있다.
     * 1. BottomSheetScaffold - Scaffold 안에 이미 BottomSheet를 만들 수 있는 옵션이 있기 때문에 간다하게 만들 수 있고 코드도 더 짧다. 대신 해당 BottomSheetScaffold을 반드시 사용해야하는 조건이 붙음
     * 2. ModalBottomSheet - 단순히 하나의 View나 Layout으로써 BottomSheet를 만들 수 있다.(즉, 독립적) 대신 BottomSheetScaffold에 비해 코드가 길고 복잡해진다.
     * 여기서는 독립적으로 사용할 수 있는 ModalBottomSheet을 사용해서 정의한다.
     *
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalBottomSheet(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.SheetState,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,kotlin.Function0,kotlin.Function1)
     * */
    @Composable
    fun ShowBottomSheetView() {
        ShowBottomSheetWidget()
    }

    /**
     * 각 종 dialog를 출력해본다
     * 1. 일반적인 버튼 클릭을 할 수 있는 dialog - 기존 AlertDialog에는 버튼을 3개까지 설정할 수 있었지만 지금은 2개만 설정가능
     * 2. 특정 날짜를 선택할 수 있는 dialog
     * 3. 특정 범위의 날짜를 선택할 수 있는 dialog
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#AlertDialog(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.ui.window.DialogProperties,kotlin.Function0)
     * */
    @Composable
    fun ShowDialog() {
        val openAlertDialogState = remember { mutableStateOf(false) }
        ShowAlertDialogWidget(openAlertDialogState)

        val openDateDialogState = remember { mutableStateOf(false) }
        ShowDateDialogWidget(openDateDialogState)

        val openDateRangeDialogState = remember { mutableStateOf(false) }
        ShowDatePickerDialogWidget(openDateRangeDialogState)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { openAlertDialogState.value = !openAlertDialogState.value }) {
                Text(text = "show Alert Dialog")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { openDateDialogState.value = !openDateDialogState.value }) {
                Text(text = "show Date Dialog")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { openDateRangeDialogState.value = !openDateRangeDialogState.value }) {
                Text(text = "show Date Range Dialog")
            }
        }
    }
}