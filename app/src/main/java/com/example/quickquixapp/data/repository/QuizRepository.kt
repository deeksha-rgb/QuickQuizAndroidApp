package com.example.quickquixapp.data.repository

import com.example.quickquixapp.data.datasource.PreferenceDataSource
import com.example.quickquixapp.data.datasource.QuizDataSource
import com.example.quickquixapp.domain.model.Question

class QuizRepository(
    private val dataSource: QuizDataSource,
    private val preferenceManager: PreferenceDataSource
) {

    fun getQuestions(): List<Question> {
        return dataSource.loadQuestions()
    }

    fun saveDarkMode(enabled: Boolean) {
        preferenceManager.saveDarkMode(enabled)
    }

    fun isDarkModeEnabled(): Boolean {
        return preferenceManager.isDarkModeEnabled()
    }
}
