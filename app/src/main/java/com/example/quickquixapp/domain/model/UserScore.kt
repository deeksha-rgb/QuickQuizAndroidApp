package com.example.quickquixapp.domain.model

/**
 * UserScore: Represents a single entry in the high scores list.
 */
data class UserScore (
    val name : String, // Name of the person who played
    val score : Int    // Score they achieved
)