package com.example.quickquixapp.data.datasource

import com.example.quickquixapp.domain.model.Question

/**
 * QuizDataSource: An interface that defines how to load questions.
 * This allows us to swap different sources (like Local JSON or Online API) easily.
 */
interface QuizDataSource {
    /**
     * loadQuestions: Should return a list of Question objects.
     */
    fun loadQuestions(): List<Question>
}