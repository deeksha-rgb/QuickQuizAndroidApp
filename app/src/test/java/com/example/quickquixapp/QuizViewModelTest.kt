package com.example.quickquixapp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickquixapp.data.FakeQuizDataSource
import com.example.quickquixapp.data.FakeQuizPreferenceManager
import com.example.quickquixapp.data.QuizRepository
import com.example.quickquixapp.ui.quiz.QuizViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuizViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        val dataSource = FakeQuizDataSource()
        val preferenceManager = FakeQuizPreferenceManager()
        val repository = QuizRepository(dataSource, preferenceManager)
        viewModel = QuizViewModel(repository)
    }

    @Test
    fun startQuiz_setsQuizStartedToTrue() {
        viewModel.startQuiz()
        assertTrue(viewModel.isQuizStarted)
    }
    @Test
    fun startQuiz_loadsFirstQuestion() {
        viewModel.startQuiz()

        val question = viewModel.currentQuestion

        assertTrue(question != null)
    }

}
