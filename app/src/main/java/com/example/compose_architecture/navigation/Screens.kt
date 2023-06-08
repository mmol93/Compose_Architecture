package com.example.compose_architecture.navigation

enum class Screens {
    Start,
    Main;

    companion object {
        fun fromRoute(route: String?): Screens =
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
    Examples,
}