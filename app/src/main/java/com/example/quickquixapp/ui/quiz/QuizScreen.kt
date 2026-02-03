package com.example.quickquixapp.ui.quiz

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickquixapp.domain.model.Question
import com.example.quickquixapp.ui.components.OptionCard
import com.example.quickquixapp.ui.components.QuizProgressBar
import com.example.quickquixapp.ui.components.QuizTimer

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    onQuizFinished: () -> Unit   // 👈 IMPORTANT
) {

    // ✅ 1. FINISH CHECK (SABSE UPAR)
    if (viewModel.quizState == QuizViewModel.QuizUiState.Finished) {
        onQuizFinished()
        return
    }

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val question = viewModel.currentQuestion
    if (question == null) {
        Spacer(modifier = Modifier.fillMaxSize())
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        QuizTimer(timeLeft = viewModel.timeLeft)

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Question ${viewModel.currentQuestionIndex + 1} of ${viewModel.totalQuestions()}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        QuizProgressBar(
            current = viewModel.currentQuestionIndex + 1,
            total = viewModel.totalQuestions()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = question.question,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isLandscape) {
            OptionsGrid(question, viewModel)
        } else {
            OptionsList(question, viewModel)
        }
    }
}

@Composable
fun OptionsList(question: Question, viewModel: QuizViewModel) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        itemsIndexed(question.options) { index, option ->
            OptionItem(option, index, question, viewModel)
        }
    }
}

@Composable
fun OptionsGrid(question: Question, viewModel: QuizViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(question.options) { index, option ->
            OptionItem(option, index, question, viewModel)
        }
    }
}

@Composable
fun OptionItem(
    option: String,
    index: Int,
    question: Question,
    viewModel: QuizViewModel
) {
    val answerState = viewModel.answerState

    val isSelected =
        answerState is QuizViewModel.AnswerState.Answered &&
                answerState.index == index

    val isCorrect =
        if (answerState is QuizViewModel.AnswerState.Answered)
            index == question.correctAnswerIndex
        else null

    OptionCard(
        text = option,
        isSelected = isSelected,
        isCorrect = isCorrect,
        onClick = { viewModel.selectOption(index) }
    )
}