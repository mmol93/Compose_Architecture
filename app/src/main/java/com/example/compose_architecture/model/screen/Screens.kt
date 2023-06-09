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