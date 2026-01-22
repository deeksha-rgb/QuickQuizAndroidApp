package com.example.quickquixapp.data

import com.example.quickquixapp.data.datasource.PreferenceDataSource
import com.example.quickquixapp.domain.model.UserScore

class FakeQuizPreferenceManager : PreferenceDataSource {

    private var scores = mutableListOf<UserScore>()
    private var darkModeEnabled = false

    override fun getUserScores(): List<UserScore> = scores.toList()

    override fun saveUserScores(scores: List<UserScore>) {
        this.scores = scores.toMutableList()
    }

    override fun clearAllUserScores() {
        scores.clear()
    }

    override fun saveDarkMode(enabled: Boolean) {
        darkModeEnabled = enabled
    }

    override fun isDarkModeEnabled(): Boolean = darkModeEnabled
}
