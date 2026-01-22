package com.example.quickquixapp.ui.highscore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickquixapp.data.local.entity.UserScoreEntity
import com.example.quickquixapp.data.repository.ScoreRepository
import kotlinx.coroutines.launch

class HighScoreViewModel(
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    var userScores by mutableStateOf<List<UserScoreEntity>>(emptyList())
        private set

    var highScore by mutableStateOf(0)
        private set

    init {
        observeScores()
    }

    private fun observeScores() {
        viewModelScope.launch {
            scoreRepository.getAllScores().collect { scores ->
                userScores = scores
                highScore = scores.maxOfOrNull { it.highScore } ?: 0
            }
        }
    }

    fun deleteScore(userName: String) {
        viewModelScope.launch {
            scoreRepository.deleteUser(userName)
        }
    }

    fun clearAllScores() {
        viewModelScope.launch {
            scoreRepository.deleteAllUsers()
        }
    }
}
