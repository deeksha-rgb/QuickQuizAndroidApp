package com.example.quickquixapp.data

import com.example.quickquixapp.data.datasource.QuizDataSource
import com.example.quickquixapp.domain.model.Question

class FakeQuizDataSource : QuizDataSource {

    override fun loadQuestions(): List<Question> {
        return listOf(
            Question(
                id = 1,
                question = "2 + 2 = ?",
                options = listOf("1", "2", "3", "4"),
                correctAnswerIndex = 3
            )
        )
    }
}
