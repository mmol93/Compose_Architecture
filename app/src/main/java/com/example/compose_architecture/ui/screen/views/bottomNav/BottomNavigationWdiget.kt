package com.example.compose_architecture.ui.screen.views.bottomNav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_architecture.ui.screen.views.ShowTempWidget
import com.example.compose_architecture.ui.screen.views.ViewScreen

@Composable
fun ViewScreen.ShowBottomNavigationWidget() {
    val items = listOf(
        BottomMenu.Profile,
        BottomMenu.FriendsList,
    )
    val navController = rememberNavController()
    var showBadge by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            // Material3에는 BottomNavigation이 없다.
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { bottomMenu ->
                    BottomNavigationItem(
                        icon = {
                            // Badge 추가
                            if (bottomMenu.haveBadge) BadgedBox(badge = {
                                Badge {
                                    // Badge에 들어가는 Text의 폰트 사이즈는 10.sp가 제일 좋은거 같음
                                    Text(text = "8", fontSize = 10.sp)
                                }
                            }) {
                                // BottomNavigation에 사용될 메뉴 아이콘
                                Icon(bottomMenu.icon, contentDescription = null)
                            } else Icon(bottomMenu.icon, contentDescription = null)
                        },
                        label = { Text(stringResource(bottomMenu.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == bottomMenu.route } == true,
                        // 클릭됐을 때 리플 효과의 색상 정의
                        selectedContentColor = Color.Red,
                        // false일 경우 선택되어있을 때만 label을 표시한다
                        alwaysShowLabel = false,
                        onClick = {
                            navController.navigate(bottomMenu.route) {
                                bottomMenu.haveBadge = false
                                // popUpTo를 사용하여 중복된 페이지의 stack을 방지하면서 추가한다.
                                popUpTo(navController.graph.findStartDestination().id) {
                                    // navigate할 때 마다 상태를 유지하게 한다.
                                    saveState = true
                                }
                                // 같은 페이지를 여러 번 열 수 없게 한다.
                                launchSingleTop = true
                                // 이전에 저장된 상태가 있는 경우 그것을 복원 시킨다.
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomMenu.Profile.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomMenu.Profile.route) { ShowTempWidget(BottomMenu.Profile.route) }
            composable(BottomMenu.FriendsList.route) { ShowTempWidget(BottomMenu.FriendsList.route) }
        }
    }
}