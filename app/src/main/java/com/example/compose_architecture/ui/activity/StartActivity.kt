package com.example.compose_architecture.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.compose_architecture.model.screen.StartScreens
import com.example.compose_architecture.screen.UiState
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme
import com.example.compose_architecture.viewModel.StartActivityViewModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StartActivity : ComponentActivity() {
    private val viewModel: StartActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        var uiState: UiState by mutableStateOf(UiState.Loading)

        val onBoardingTitleList = listOf("Welcome", "Compose Architecture", "Start Now")
        val onBoardingImages =
            listOf(
                "https://play-lh.googleusercontent.com/KwUBNPbMTk9jDXYS2AeX3illtVRTkrKVh5xR1Mg4WHd0CG2tV4mrh1z3kXi5z_warlk",
                "https://store-images.microsoft.com/image/apps.21169.9007199266244427.cc23e1b0-9845-4273-918c-f8dbdb058401.ebc29770-cc7b-4af1-89cc-2085c1498f24",
                "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201812022340"
            )

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
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
            val navController = rememberNavController()

            Compose_ArchitectureTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowOnBoarding(
                        context = this,
                        titles = onBoardingTitleList,
                        images = onBoardingImages,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun SetScreen(context: Context, titles: List<String>, images: List<String>) {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = StartScreens.fromRoute(backStackEntry.value?.destination?.route)

    NavHost(navController = navController, startDestination = StartScreens.Start.name) {
        composable(StartScreens.Start.name) {
            ShowOnBoarding(
                context = context,
                titles = titles,
                images = images,
                navController = navController
            )
        }

        composable(StartScreens.Main.name) {
            Main()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowOnBoarding(
    context: Context,
    titles: List<String>,
    images: List<String>,
    navController: NavController
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            style = TextStyle(fontSize = 32.sp),
            text = "OnBoarding Test"
        )
        // ViewPager (Experimental)
        HorizontalPager(pageCount = titles.size, state = pagerState) {
            AsyncImage(
                model = images[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            style = TextStyle(fontSize = 20.sp),
            text = titles[pagerState.currentPage]
        )
        // ViewPager Indicator (Experimental)
        HorizontalPagerIndicator(
            modifier = Modifier
                .padding(bottom = 10.dp),
            pageCount = titles.size,
            pagerState = pagerState,
        )
        if (pagerState.currentPage == titles.size - 1) {
            Button(modifier = Modifier.padding(12.dp), onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
                (context as Activity).finish()
            }) {
                Text(text = "Next Page")
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "asdfsa")
    }
}
