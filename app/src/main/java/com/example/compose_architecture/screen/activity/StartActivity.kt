package com.example.compose_architecture.screen.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose_architecture.screen.UiState
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme
import com.example.compose_architecture.viewModel.StartActivityViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StartActivity : ComponentActivity() {

    val viewModel: StartActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        var uiState: UiState by mutableStateOf(UiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }.collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                UiState.Loading -> true
                is UiState.Success -> false
            }
        }

        // set UI showing after Splash
        setContent {
            // Turn off the decor fitting system windows, which allows us to handle insets,
            // including IME animations
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Compose_ArchitectureTheme {
        Greeting2("Android")
    }
}
