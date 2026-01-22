package com.example.quickquixapp.data.datasource

import com.example.quickquixapp.domain.model.Question

/**
 * QuestionResponse
 * Matches the structure of questions.json
 */
data class QuestionResponse(
    val questions: List<Question>
)
