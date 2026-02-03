package com.example.quickquixapp.ui.navigation

sealed class NavRoute(val route: String) {

    object Home : NavRoute("home")

    object EnterName : NavRoute("enter_name")

    object Quiz : NavRoute("quiz/{userName}/{difficulty}") {
        fun createRoute(userName: String, difficulty: String) =
            "quiz/$userName/$difficulty"
    }

    object Result : NavRoute("result/{score}/{total}") {
        fun createRoute(score: Int, total: Int) =
            "result/$score/$total"
    }

    object HighScore : NavRoute("highscore")
}