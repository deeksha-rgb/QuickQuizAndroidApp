package com.example.quickquixapp.ui.navigation

/**
 * NavRoute: A simple class to keep track of all the "Addresses" in our app.
 * Each object represents a different screen that the user can visit.
 */
sealed class NavRoute(val route: String) {
    object EnterName : NavRoute("enter_name")
    object Home : NavRoute("home")      // The Start Screen
    object Quiz : NavRoute("quiz")      // The Question Screen
    object Result : NavRoute("result")  // The Final Score Screen
    object HighScore : NavRoute("highscore") // The Leaderboard Screen
}
