package com.example.quickquixapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickquixapp.ui.highscore.HighScoreViewModel
import com.example.quickquixapp.ui.quiz.QuizViewModel
import com.example.quickquixapp.ui.home.HomeViewModel

class ViewModelFactory(
    private val appContainer: AppContainer
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            //  HomeViewModel
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    quizRepository = appContainer.quizRepository
                ) as T
            }

            // QuizViewModel
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel(
                    quizRepository = appContainer.quizRepository,
                    scoreRepository = appContainer.scoreRepository
                ) as T
            }

            //  HighScoreViewModel
            modelClass.isAssignableFrom(HighScoreViewModel::class.java) -> {
                HighScoreViewModel(
                    scoreRepository = appContainer.scoreRepository
                ) as T
            }

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class: ${modelClass.name}"
            )
        }
    }
}
