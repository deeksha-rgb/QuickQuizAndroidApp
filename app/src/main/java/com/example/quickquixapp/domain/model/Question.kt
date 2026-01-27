package com.example.quickquixapp.domain.model

/**
 * Question: A simple data class to represent a single quiz question.
 */

data class Question(
    val id: Int, // Unique ID for the question
    val question: String, // The actual question text
    val options: List<String>, // List of 4 possible answers
    val correctAnswerIndex: Int, // Index of correct option
    val difficulty: Difficulty // EASY / MEDIUM / HARD
)
