package com.example.quickquixapp.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickquixapp.ui.components.AnimatedPrimaryButton

/**
 * ResultScreen
 *
 * Pure UI screen.
 * Shows final quiz result.
 * No ViewModel required.
 */
@Composable
fun ResultScreen(
    score: Int,
    totalQuestions: Int,
    highScore: Int,
    onRestart: () -> Unit,
    onViewHighScores: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ---------- TITLE ----------
        Text(
            text = "Your Score",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ---------- SCORE ----------
        Text(
            text = "$score / $totalQuestions",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- MESSAGE ----------
        Text(
            text = if (score >= totalQuestions / 2)
                "Great job!"
            else
                "Try again!",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- ACTIONS ----------
        AnimatedPrimaryButton(
            text = "Restart Quiz",
            onClick = onRestart,
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedPrimaryButton(
            text = "View High Scores",
            onClick = onViewHighScores,
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- HIGH SCORE ----------
        Text(
            text = "High Score: $highScore",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
