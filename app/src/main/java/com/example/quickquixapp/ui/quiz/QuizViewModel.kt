package com.example.quickquixapp.ui.quiz

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickquixapp.data.repository.QuizRepository
import com.example.quickquixapp.data.repository.ScoreRepository
import com.example.quickquixapp.domain.model.Difficulty
import com.example.quickquixapp.domain.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    // ---------- STATE ----------
    sealed class QuizUiState {
        object Idle : QuizUiState()
        object Playing : QuizUiState()
        object Finished : QuizUiState()
    }

    var quizState by mutableStateOf<QuizUiState>(QuizUiState.Idle)
        private set

    // ---------- TIMER ----------
    private var timerJob: Job? = null

    var timeLeft by mutableIntStateOf(10)
        private set

    // ---------- USER ----------
    private var userName: String = ""

    // ---------- QUESTIONS ----------
    private var questions: List<Question> = emptyList()

    var currentQuestionIndex by mutableIntStateOf(0)
        private set

    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

    fun totalQuestions(): Int = questions.size

    // ---------- ANSWER ----------
    sealed class AnswerState {
        object NotAnswered : AnswerState()
        data class Answered(val index: Int) : AnswerState()
    }

    var answerState by mutableStateOf<AnswerState>(AnswerState.NotAnswered)
        private set

    // ---------- SCORE ----------
    var score by mutableIntStateOf(0)
        private set

    // ---------- QUIZ START (ONLY ONCE) ----------
    fun startQuiz(userName: String, difficulty: Difficulty) {
        if (quizState == QuizUiState.Playing) return

        this.userName = userName
        questions = quizRepository.getQuestions(difficulty)

        currentQuestionIndex = 0
        score = 0
        timeLeft = 10
        answerState = AnswerState.NotAnswered
        quizState = QuizUiState.Playing

        startTimer()
    }

    fun selectOption(index: Int) {
        if (answerState is AnswerState.Answered) return

        answerState = AnswerState.Answered(index)
        stopTimer()

        viewModelScope.launch {
            delay(400)
            nextQuestion()
        }
    }

    private fun nextQuestion() {
        val question = currentQuestion ?: return

        if (
            answerState is AnswerState.Answered &&
            (answerState as AnswerState.Answered).index == question.correctAnswerIndex
        ) {
            score++
        }

        answerState = AnswerState.NotAnswered

        if (currentQuestionIndex < questions.lastIndex) {
            currentQuestionIndex++
            startTimer()
        } else {
            finishQuiz()
        }
    }

    fun restartQuiz() {
        stopTimer()
        quizState = QuizUiState.Idle
        currentQuestionIndex = 0
        score = 0
        timeLeft = 10
        answerState = AnswerState.NotAnswered
    }

    // ---------- TIMER ----------
    private fun startTimer() {
        timerJob?.cancel()
        timeLeft = 10

        timerJob = viewModelScope.launch {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            if (answerState is AnswerState.NotAnswered) {
                nextQuestion()
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    // ---------- FINISH ----------
    private fun finishQuiz() {
        stopTimer()
        quizState = QuizUiState.Finished
        saveScore()
    }

    private fun saveScore() {
        if (userName.isBlank()) return
        viewModelScope.launch {
            scoreRepository.saveScore(userName, score)
        }
    }
}