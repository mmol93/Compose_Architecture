package com.example.compose_architecture.ui.screen.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.web.*

@Composable
fun ViewScreen.ShowWebWidget(navHostController: NavHostController) {
    // 웹뷰에 필요한 클라이언트 생성
    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()

    val webViewNavigator = rememberWebViewNavigator()

    Column() {
        val webViewState =
            rememberWebViewState(
                url = "https://google.com",
                additionalHttpHeaders = emptyMap()
            )

        WebView(
            state = webViewState,
            // 여기에 webView navigator를 등록한다.
            navigator = webViewNavigator,
            client = webViewClient,
            chromeClient = webChromeClient,
            onCreated = { webView ->
                with(webView) {
                    settings.run {
                        // JS 사용 가능하도록
                        javaScriptEnabled = true
                        // 로컬 스토리지와 세션 스토리지를 사용할 수 있도록 설정
                        domStorageEnabled = true
                        // JS가 자동으로 새 창을 열 수 없도록 설정
                        javaScriptCanOpenWindowsAutomatically = false
                    }
                    // 웹 상의 JS를 사용해서 앱의 기능을 사용하도록 한다.
                    // JS에서 "Bridge"라는 함수를 호출하도록 하면 앱은 test()를 호출한다.
                    addJavascriptInterface(test(), "Bridge")
                }
            }
        )

        // 웹뷰의 뒤로가기 제어
        BackHandler(enabled = true) {
            if (webViewNavigator.canGoBack) {
                webViewNavigator.navigateBack()
            } else {
                navHostController.popBackStack()
            }
        }
    }
}

fun test() {

}