package com.example.compose_architecture.ui.screen.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme

@Composable
fun ShowImageWidget() {
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