package com.example.compose_architecture.model.screen

enum class StartScreens {
    Start,
    Main;

    companion object {
        fun fromRoute(route: String?): StartScreens =
            when (route?.substringBefore("/")) {
                Start.name -> Start
                Main.name -> Main
                null -> Main
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

enum class MainScreens {
    View,
    Functions,
    Animations,
    Examples,
}

/**
 * 아래의 모든 Screen을 SetMainScreenWithNavigation에 경로 셋팅해야함
 * */
enum class ViewScreens {
    ImageView,
    DrawerM2,
    DrawerM3,
    TopAppBar,
    CollapsingTopAppBar,
    TextView,
    BottomSheet,
    Dialog,
    SnackBar,
    PullRefresh,
    BottomNavigation,
    Card,
    Temp,
    List,
    WebView,
    SearchBar,
}

enum class ExampleScreens {
    SplitFlap,
}