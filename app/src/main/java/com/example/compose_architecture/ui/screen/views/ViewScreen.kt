package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
                    style = MaterialTheme.typography.headlineMedium,
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
     * 컴포즈의 ImageView에 대한 예시
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
     * 컴포즈의 Drawer에 대한 예시
     * */
    @Composable
    fun ShowDrawer() {
        val scaffoldState = rememberScaffoldState()
        val drawerItemList = listOf("내 정보", "게임", "설정")
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
                        Row(modifier = Modifier.clickable { }) {
                            Image(
                                painter = painterResource(id = R.drawable.android_logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(64.dp)
                                    .padding(8.dp)
                            )
                            androidx.compose.material3.Text(
                                text = drawerItemList[index],
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Divider(color = Color.Gray, thickness = 2.dp)
                    }
                }
            },
        ) {
            Column(modifier = Modifier.padding(it)) {

            }
        }
    }
}