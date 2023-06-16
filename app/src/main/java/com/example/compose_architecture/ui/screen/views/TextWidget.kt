package com.example.compose_architecture.ui.screen.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_architecture.R
import com.example.compose_architecture.util.showToast

@Composable
fun ShowTextWidget(context: Context) {
    val spacer = 20.dp

    // 테스트용 URL을 담고 있는 텍스트
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // 해당 텍스트(annotation)을 URL로써 다음 텍스트(here)에 등록한다.
        // 다음 텍스트의 기준은 pop()이 나오기 전까지의 모든 텍스트임
        pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("here")
        }
        pop()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // resource로 텍스트 지정 + 간단한 textStyle 지정하기
        // FontFamily를 사용해서 프로젝트 내에 있는 폰트 파일 사용 가능 - https://developer.android.com/jetpack/compose/text?hl=ko#fonts
        Text(text = "텍스트 지정 및 스타일(색, 크기, 스타일) 지정", fontSize = 12.sp)
        Text(
            text = stringResource(id = R.string.test_text_string),
            color = Color.Gray,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(spacer))

        // 텍스트 정렬 - 해당 뷰의 공간에 대해 정렬을 실시한다. 그렇기 때문에 fillMaxWidth 필요(부모 컨테이너 뷰에 대한 정렬 아님)
        Text(text = "텍스트 뷰 안에서 텍스트 정렬", fontSize = 12.sp)
        Text(
            text = stringResource(id = R.string.test_text_string),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(spacer))

        // 동일한 Text에 서로 다른 Style 적용하기
        Text(text = "하나의 텍스트에 텍스트별로 여러 스타일 지정하기", fontSize = 12.sp)
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("H")
            }
            append("ello ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        })
        Spacer(modifier = Modifier.height(spacer))

        // 텍스트 줄 수 제한하기
        Text(text = "텍스트 줄 수 제어하기", fontSize = 12.sp)
        Text(text = "repeat ".repeat(25), maxLines = 2, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.height(spacer))

        // 유저가 복사 가능한 텍스트 - 기본적으로 Text는 선택할 수 없다.
        Text(text = "사용자가 선택 가능한(복사 등) 텍스트로 만들어주기", fontSize = 12.sp)
        SelectionContainer {
            Column {
                Text("This text is selectable")
                Spacer(modifier = Modifier.height(4.dp))

                // 그 중에서 일부분은 선택할 수 없는 텍스트를 만들 수 있음
                DisableSelection {
                    Text("This text is not selectable")
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer))

        // URL 태그를 갖는 annotation 텍스트만 가져와서 해당 텍스트를 클릭할 경우 동작하게 한다.
        // 즉, 기존 textView에 span을 사용해서 특정 부분만 클릭할 수 있게 한거랑 비슷한 기능.
        Text(text = "기존의 textSpan 처럼 텍스트 일부분을 클릭할 수 있게 만들기", fontSize = 12.sp)
        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                // We check if there is an *URL* annotation attached to the text
                // at the clicked position
                annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        // If yes, it will show Toast
                        showToast(context = context, text = annotation.item)
                    }
            }
        )
        Spacer(modifier = Modifier.height(spacer))

        // import androidx.compose.runtime.setValue for remember
        // import androidx.compose.runtime.getValue for remember
        var text by remember { mutableStateOf("Test Text") }
        // 채워지지 않은 TextField
        // 채워진건 그냥 TextField
        Text(text = "입력할 수 있는 에디트 텍스트", fontSize = 12.sp)
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
        Spacer(modifier = Modifier.height(spacer))

        // 입력 형식 지정 (예: 비밀번호 입력)
        // rememberSaveable: 앱을 종료하더라고 해당 값을 유지하고 있는다.
        var password by rememberSaveable { mutableStateOf("") }
        Text(text = "에디트 텍스트에 입력 형식 지정하기", fontSize = 12.sp)
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            // 더 많은 입력 양식은 여기 - https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-text/samples/src/main/java/androidx/compose/ui/text/samples/VisualTransformationSamples.kt?hl=ko
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(spacer))

        // 입력 받은 문자열을 변환
        var inputTrimStart by remember { mutableStateOf("") }
        Text(text = "에디트 텍스트에 입력할 수 있는 문자열 제어하기", fontSize = 12.sp)
        TextField(
            value = inputTrimStart,
            onValueChange = { newText ->
                // 제일 처음 오는 문자열로 0을 사용할 수 없음
                inputTrimStart = newText.trimStart { it == '0' }
            }
        )
        var inputTrim by remember { mutableStateOf("") }
        TextField(
            value = inputTrim,
            onValueChange = { newText ->
                // 문자 0 을 사용할 수 없음. 사용할 경우 Toast 보여줌
                inputTrim = newText.trim {
                    if (it == '0') {
                        showToast(context = context, text = "you can't put '0'")
                        true
                    } else false
                }
            }
        )
        Spacer(modifier = Modifier.height(spacer))
    }
}