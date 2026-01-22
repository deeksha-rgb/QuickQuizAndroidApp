package com.example.quickquixapp.ui.home



import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.quickquixapp.data.repository.QuizRepository

class HomeViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    val isNameValid by derivedStateOf {
        userName.trim().isNotEmpty()
    }

    var isDarkMode by mutableStateOf(false)
        private set

    init {
        isDarkMode = quizRepository.isDarkModeEnabled()
    }

    fun updateUserName(name: String) {
        userName = name

    }

    fun toggleDarkMode() {
        isDarkMode = !isDarkMode
        quizRepository.saveDarkMode(isDarkMode)
    }
}
