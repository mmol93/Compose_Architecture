package com.example.compose_architecture.ui.screen.examples

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Flap이 회전하며 원하는 텍스트를 출력해준다.
 * Flap의 배경색 등을 수정할 수 있다.
 * */

// TODO: 지금 버전에서는 원하는 텍스트가 나올 때까지 여러번 회전하지만, 조금 수정하면 한 번만 회전해서 원하는 텍스트가 나오게 할 수 있을거 같다.
@Composable
fun ShowSplitFlap(text: String = "01 PARIS-BERLIN") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(12.dp)
    ) {
        Row {
            Text("VIA", color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(56.dp))
            Text("Route", color = Color.White, fontSize = 12.sp)
        }
        Row {
            // 입력 받은 텍스트를 SplitFlap으로 표시
            text.forEach {
                ShowSingleSplitFlap(
                    character = it,
                    color = Color.White,
                    // Flap Box의 사이즈
                    modifier = Modifier.size(20.dp, 28.dp)
                )
            }
        }
    }
}

/**
 * 하나의 풀사이즈 Flap 박스를 의미한다.
 * @param character 풀사이즈 Flap 박스에 들어가는 하나의 문자
 * @param color 풀사이즈 Flap 박스에 들어가는 텍스트 색상
 * @param modifier 풀사이즈 Flap 박스의 modifier
 * */
@Composable
fun ShowSingleSplitFlap(
    character: Char,
    color: Color,
    modifier: Modifier = Modifier
) {
    // 여기 문자열에는 표시할 모든 문자를 포함하고 있어야한다.
    val characters = " -1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ#$€:,."
    var pastChar by remember { mutableStateOf(characters.first()) }
    var finalChar by remember { mutableStateOf(character) }
    var indicatorRotation by remember { mutableStateOf(0f) }
    val animationDuration = 200
    val animationSpec =
        tween<Float>(animationDuration, easing = CubicBezierEasing(0.3f, 0.0f, 0.8f, 0.8f))

    LaunchedEffect(character) {
        // characters 안에 현재 문자와 일치하는 문자가 있는지 확인한다.
        val index = characters.indexOf(character)

        // 해당 인덱스가 될 때까지 애니메이션을 실시한다.
        if (index >= 0) {
            (0..index).forEach {
                finalChar = characters[it]
                // 0 ~ -180까지 순회하며 애니메이션을 실시한다. = value에 값을 넣는다.
                animate(
                    initialValue = 0f,
                    targetValue = -180f,
                    animationSpec = animationSpec
                ) { value, _ ->
                    indicatorRotation = value
                    if (value <= -175f) {
                        pastChar = characters[it]
                    }
                }
            }
        } else {
            throw Exception("characters에 포함하지 않은 문자가 존재합니다.")
        }
    }

    Box(modifier.background(Color(0xFF222222))) {
        // 위쪽 SplitFlapPiece
        SplitFlapPiece(displayText = finalChar, color = color, modifier = Modifier
            // graphicsLayer를 사용해서 렌더링일 커스텀할 수 있다.
            .graphicsLayer {
                // 뷰를 잘라낼 수 있게 한다.
                clip = true
                // 잘라낼 모양을 지정한다.
                shape = object : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {
                        return Outline.Rectangle(
                            size
                                // -2를 줌으로써 두 박스 사이에 선이 생기게 한다
                                .copy(height = size.height / 2 - 2, width = size.width)
                                // 이후 직사각형 모양으로 만든다.
                                .toRect()
                        )
                    }
                }
            }
            .background(Color.DarkGray)
            .border(BorderStroke(Dp.Hairline, Color(0xFF222222)))
        )

        // 아래쪽 SplitFlapPiece
        SplitFlapPiece(displayText = pastChar, color = color, modifier = Modifier
            .graphicsLayer {
                clip = true
                rotationX = -180f
                rotationY = 180f
                rotationZ = 180f
                shape = object : Shape {
                    override fun createOutline(
                        size: Size,
                        layoutDirection: LayoutDirection,
                        density: Density
                    ): Outline {
                        return Outline.Rectangle(
                            size
                                .copy(height = size.height / 2, width = size.width)
                                .toRect()
                                // 밑으로 기존 높이의 1/2만큼 이동시킨다.
                                .translate(0f, size.height / 2)
                        )
                    }
                }
            }
            .background(Color.DarkGray)
            .border(BorderStroke(Dp.Hairline, Color(0xFF222222)))
        )

        // 회전하는 위쪽 SplitFlapPiece
        if (indicatorRotation >= -90f) {
            SplitFlapPiece(displayText = pastChar, color = color, modifier = Modifier
                .graphicsLayer {
                    // X 축 기준으로 회전하도록 한다.
                    rotationX = indicatorRotation
                    clip = true
                    shape = object : Shape {
                        override fun createOutline(
                            size: Size,
                            layoutDirection: LayoutDirection,
                            density: Density
                        ): Outline {
                            return Outline.Rectangle(
                                size
                                    .copy(height = size.height / 2 - 2, width = size.width)
                                    .toRect()
                            )
                        }
                    }
                }
                .background(Color.DarkGray)
                .border(BorderStroke(Dp.Hairline, Color(0xFF222222)))
            )
        } else {
            // 회전하는 아랫쪽 SplitFlapPiece
            SplitFlapPiece(displayText = finalChar, color = color, modifier = Modifier
                .graphicsLayer {
                    clip = true
                    rotationX = indicatorRotation
                    rotationY = 180f
                    rotationZ = 180f
                    shape = object : Shape {
                        override fun createOutline(
                            size: Size,
                            layoutDirection: LayoutDirection,
                            density: Density
                        ): Outline {
                            return Outline.Rectangle(
                                size
                                    .copy(height = size.height / 2, width = size.width)
                                    .toRect()
                                    .translate(0f, size.height / 2)
                            )
                        }
                    }
                    shadowElevation = 1f
                }
                .background(Color.DarkGray)
                .border(BorderStroke(Dp.Hairline, Color(0xFF222222)))
            )
        }
    }
}

/**
 * SplitFlapPiece는 하나의 flap 박스 조각을 의미한다.(위 아래 중 하나)
 * @param displayText SplitFlapPiece에서 표시할 문자
 * @param color 풀사이즈 Flap 박스에 들어가는 텍스트 색상
 * @param modifier 풀사이즈 Flap 박스의 modifier
 * */
@Composable
private fun SplitFlapPiece(displayText: Char, color: Color, modifier: Modifier) {
    val boltGradient = Brush.verticalGradient(
        colors = listOf(
            Color.DarkGray,
            Color.Black,
            Color.DarkGray,
            Color.Black,
            Color.DarkGray,
            Color.Black,
            Color.DarkGray,
            Color.Black,
            Color.DarkGray,
            Color.Black
        ),
        tileMode = TileMode.Repeated,
    )
    BoxWithConstraints {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .then(modifier)
        ) {
            // Flip의 윗쪽 Box
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {}
            // Flip에서 Text가 출력되는 부분
            Text(
                displayText.toString(),
                color = color,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                // 사이즈가 Box를 넘지만 않으면 올바르게 중앙에 표시됨
                fontSize = 12.sp
            )
            // Flip의 아랫쪽 Box
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {}
        }

        // Decoration
        Box(
            Modifier
                .size(maxWidth / 15, maxHeight / 5)
                .background(Color(0xFF222222))
                .align(Alignment.CenterStart)
        )
        Box(
            Modifier
                .size(maxWidth / 15 - 1.dp, maxHeight / 5 - 2.dp)
                .offset(x = 0.5.dp)
                .background(boltGradient)
                .align(Alignment.CenterStart)
        )
        Box(
            Modifier
                .size(maxWidth / 15, maxHeight / 5)
                .background(Color(0xFF222222))
                .align(Alignment.CenterEnd)
        )
        Box(
            Modifier
                .size(maxWidth / 15 - 1.dp, maxHeight / 5 - 2.dp)
                .offset(x = (-0.5).dp)
                .background(boltGradient)
                .align(Alignment.CenterEnd)
        )
    }
}