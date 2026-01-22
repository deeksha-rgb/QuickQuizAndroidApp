package com.example.quickquixapp.ui.quiz

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickquixapp.data.repository.QuizRepository
import com.example.quickquixapp.data.repository.ScoreRepository
import com.example.quickquixapp.domain.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    // ---------- QUIZ UI STATE ----------
    sealed class QuizUiState {
        object Idle : QuizUiState()
        object Playing : QuizUiState()
        object Finished : QuizUiState()
    }
    private var timerJob: Job? = null

    var timeLeft by mutableStateOf(10)
        private set

    var quizState by mutableStateOf<QuizUiState>(QuizUiState.Idle)
        private set
    private var isProcessingNext = false


    // ---------- USER ----------
    var userName by mutableStateOf("")




    // ---------- QUESTIONS ----------
    private val questions: List<Question> = quizRepository.getQuestions()

    var currentQuestionIndex by mutableStateOf(0)
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

    var score by mutableStateOf(0)
        private set



    // ---------- ACTIONS ----------

    fun startQuiz() {
        quizState = QuizUiState.Playing
        startTimer()
    }

    fun selectOption(index: Int) {
        if (answerState is AnswerState.Answered) return
        answerState = AnswerState.Answered(index)
        stopTimer()
    }

    fun nextQuestion() {
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
            quizState = QuizUiState.Finished
            saveScoreToRoom()
            stopTimer()
        }
    }



    fun restartQuiz() {
       stopTimer()
        quizState = QuizUiState.Idle
        currentQuestionIndex = 0
        score = 0
        answerState = AnswerState.NotAnswered
    }

    private fun startTimer() {
        timerJob?.cancel()
        timeLeft = 10

        timerJob = viewModelScope.launch {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            nextQuestion()
        }
    }
    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }


    // ---------- ROOM DB SAVE ----------
    private fun saveScoreToRoom() {
        if (userName.isBlank()) return   // safety

        viewModelScope.launch {
            scoreRepository.saveScore(
                userName = userName,
                score = score
            )
        }
    }
}
