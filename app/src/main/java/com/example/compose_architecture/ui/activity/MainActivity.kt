package com.example.compose_architecture.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_architecture.model.screen.ExampleScreens
import com.example.compose_architecture.model.screen.MainScreens
import com.example.compose_architecture.model.screen.StartScreens
import com.example.compose_architecture.model.screen.ViewScreens
import com.example.compose_architecture.ui.screen.examples.ExampleScreen
import com.example.compose_architecture.ui.screen.examples.ExampleScreen.ShowSplitFlapScreen
import com.example.compose_architecture.ui.screen.examples.lazyColumn.LazyColumnViewModel
import com.example.compose_architecture.ui.screen.examples.lazyColumn.ShowLazyColumnWidget
import com.example.compose_architecture.ui.screen.functions.FunctionScreen
import com.example.compose_architecture.ui.screen.views.*
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val lazyColumnViewModel: LazyColumnViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_ArchitectureTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetMainScreenWithNavigation(this, navController)
                }
            }
        }
    }

    @Composable
    fun SetMainScreenWithNavigation(context: Context, navHostController: NavHostController) {
        NavHost(navController = navHostController, startDestination = StartScreens.Main.name) {
            // ---- View -----
            composable(StartScreens.Main.name) {
                ShowHomeScreen(navHostController = navHostController)
            }
            composable(MainScreens.View.name) {
                ViewScreen.ShowViewScreen(mainActivityNavHostController = navHostController)
            }
            composable(MainScreens.Animations.name) {

            }
            composable(ViewScreens.ImageView.name) {
                ViewScreen.ShowImageView()
            }
            composable(ViewScreens.DrawerM2.name) {
                ViewScreen.ShowDrawerM2()
            }
            composable(ViewScreens.DrawerM3.name) {
                ViewScreen.ShowDrawerM3()
            }
            composable(ViewScreens.TopAppBar.name) {
                ViewScreen.ShowTopAppBar(navHostController = navHostController)
            }
            composable(ViewScreens.Temp.name) {
                ViewScreen.ShowTempScreen()
            }
            composable(ViewScreens.CollapsingTopAppBar.name) {
                ViewScreen.ShowCollapsingTopAppBar()
            }
            composable(ViewScreens.TextView.name) {
                ViewScreen.ShowTextViews(context = context)
            }
            composable(ViewScreens.BottomSheet.name) {
                ViewScreen.ShowBottomSheetView()
            }
            composable(ViewScreens.Dialog.name) {
                ViewScreen.ShowDialog()
            }

            composable(ViewScreens.SnackBar.name) {
                ViewScreen.ShowSnackBar()
            }
            composable(ViewScreens.PullRefresh.name) {
                ViewScreen.ShowPullRefresh()
            }
            composable(ViewScreens.BottomNavigation.name) {
                ViewScreen.ShowBottomNavigation()
            }
            composable(ViewScreens.Card.name) {
                ViewScreen.ShowCard()
            }
            composable(ViewScreens.List.name) {
                ViewScreen.ShowLazyColumnWidget(lazyColumnViewModel = lazyColumnViewModel)
            }
            composable(ViewScreens.WebView.name) {
                ViewScreen.ShowWebWidget(navHostController = navHostController)
            }
            composable(ViewScreens.SearchBar.name) {
                ViewScreen.ShowSearchBarWidget()
            }
            composable(ViewScreens.BottomAppBar.name) {
                ViewScreen.ShowBottomAppbarWidget()
            }

            // ---- Function -----
            composable(MainScreens.Functions.name) {
                FunctionScreen.ShowFunctionScreen(mainActivityNavHostController = navHostController)
            }

            // ---- Example -----
            composable(MainScreens.Examples.name) {
                ExampleScreen.ShowExampleScreen(mainActivityNavHostController = navHostController)
            }
            composable(ExampleScreens.SplitFlap.name) {
                ExampleScreen.ShowSplitFlapScreen()
            }
            composable(ExampleScreens.LazyColumnWithPaging3.name) {
                ViewScreen.ShowLazyColumnWidget(lazyColumnViewModel = lazyColumnViewModel)
            }
        }
    }
}

@Composable
private fun ShowHomeScreen(navHostController: NavHostController) {
    Column {
        LazyColumn {
            items(count = MainScreens.values().size) { index ->
                Text(
                    text = MainScreens.values()[index].name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(MainScreens.values()[index].name)
                        }
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }
}
