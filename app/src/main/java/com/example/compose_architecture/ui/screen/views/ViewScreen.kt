package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_architecture.R
import com.example.compose_architecture.model.screen.ViewScreens
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme
import kotlinx.coroutines.launch

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
        val spacerHeight = 20.dp
        val imageUrl =
            "https://store-images.microsoft.com/image/apps.21169.9007199266244427.cc23e1b0-9845-4273-918c-f8dbdb058401.ebc29770-cc7b-4af1-89cc-2085c1498f24"
        Compose_ArchitectureTheme {
            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }

            val scrollState = rememberScrollState()
            Surface() {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    // 둥근 이미지 만들기
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            // 원형으로 만들기
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(spacerHeight))

                    // 라운딩 처리된 이미지 만들기
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            // 라운딩 효과
                            .clip(shape = RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(spacerHeight))

                    // 둥근 테두리 씌우기
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(CircleShape)
                            // 테두리 생성
                            .border(BorderStroke(4.dp, rainbowColorsBrush), CircleShape)
                    )

                    Spacer(modifier = Modifier.height(spacerHeight))

                    // 특정 비율의 이미지 만들기
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(CircleShape)
                            // 16:9 비율로 만들기
                            .aspectRatio(16f / 9f)
                    )

                    Spacer(modifier = Modifier.height(spacerHeight))

                    // 이미지에 모자이크 처리하기
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(CircleShape)
                            // 이미지에 모자이크 효과를 넣는다.
                            .blur(
                                // 숫자가 높을수록 강한 모자이크가 들어간다.
                                radiusX = 20.dp,
                                radiusY = 20.dp,
                                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                            )
                    )
                }
            }
        }
    }

    /**
     * 컴포즈의 Material2를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/jetpack/compose/layouts/material?hl=ko
     * */
    @Composable
    fun ShowDrawerM2() {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val drawerItemList = listOf("내 정보", "게임", "설정")
        val drawerIconList = listOf(
            R.drawable.baseline_supervised_user_circle_24,
            R.drawable.baseline_videogame_asset_24,
            R.drawable.baseline_settings_24
        )
        // drawer를 사용하기 위해서 Scaffold를 사용하지 않아도 되지만 사용하는 것이 좋다.
        Scaffold(
            scaffoldState = scaffoldState,
            // Drawer를 만드는 방법은 다른 뷰를 만드는 것과 거의 동일함
            drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                // LazyColum을 사용해서 만들면 혹시 휴대폰이 작아서 모든 메뉴가 표시되지 않더라도 스크롤 할 수 있다.
                LazyColumn {
                    items(count = drawerItemList.size) { index ->
                        Row(
                            modifier = Modifier.clickable { },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = drawerIconList[index]),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(64.dp)
                                    .padding(8.dp)
                            )
                            Text(
                                text = drawerItemList[index],
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            },
            // 제스처로 draw를 open / close 할 수 있는 기능은 켜고 끌 수 있다
            drawerGesturesEnabled = true
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            // drawer의 open/close는 비동기로 진행되야한다.
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Text(text = "open / close Drawer")
                }
            }
        }
    }

    /**
     * 컴포즈의 Material3를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)
     * */
    @Composable
    fun ShowDrawerM3() {
        // 둘 다 Material3를 import한 함수임에 주의
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val drawerItemList = listOf("내 정보", "게임", "설정")
        val drawerIconList = listOf(
            R.drawable.baseline_supervised_user_circle_24,
            R.drawable.baseline_videogame_asset_24,
            R.drawable.baseline_settings_24
        )
        val selectedItem = remember { mutableStateOf(drawerItemList[0]) }

        // NavigarionDrawer가 최상위 View가 된다.(Material3 임에 주의)
        // Drawer를 만드는 방법은 Material2의 drawer와 거의 동일하다.
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // ModalDrawerSheet에 modifier를 정의해서 drawer의 크기를 조절할 수 있다.
                ModalDrawerSheet(modifier = Modifier.width(250.dp)) {
                    Spacer(Modifier.height(12.dp))
                    drawerItemList.forEachIndexed { index, drawerItemTitle ->
                        // drawer의 각 아이템 정의
                        NavigationDrawerItem(
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            icon = {
                                Icon(
                                    painter = painterResource(id = drawerIconList[index]),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = drawerItemTitle,
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            },
                            // 현재 선택된 아이템을 지정해줘야한다.
                            selected = drawerItemTitle == selectedItem.value,
                            // drawer의 각 아이템을 클릭했을 때 동작
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = drawerItemTitle
                            }
                        )
                    }
                }
            },
            // content 안에 Column 등을 사용해서 그 밖의 View 들을 정의한다.
            // 즉, Layer 상으로 Drawer가 제일 위에 있고 그 아래에 다른 view들이 존재한다.
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    // 외부에서 drawerState를 사용하여 drawer를 열고 닫을 수 있다.
                    Button(onClick = { scope.launch { drawerState.open() } }) {
                        Text("Click to open")
                    }
                }
            }
        )
    }
}