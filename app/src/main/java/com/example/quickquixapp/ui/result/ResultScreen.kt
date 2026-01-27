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
import com.example.quickquixapp.ui.components.ScreenContainer

/**
 * ResultScreen
 *
 * Pure UI screen.
 * Uses app theme colors (Light + Dark).
 */
@Composable
fun ResultScreen(
    score: Int,
    totalQuestions: Int,
    highScore: Int,
    onRestart: () -> Unit,
    onViewHighScores: () -> Unit
) {
    ScreenContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  TITLE
            Text(
                text = "Your Score",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            //  SCORE
            Text(
                text = "$score / $totalQuestions",
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(14.dp))

            //  FEEDBACK MESSAGE
            Text(
                text = if (score >= totalQuestions / 2)
                    "Great job! 🎉"
                else
                    "Try again 💪",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f)
            )

            Spacer(modifier = Modifier.height(28.dp))

            //  RESTART
            AnimatedPrimaryButton(
                text = "Restart Quiz",
                onClick = onRestart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            //  HIGH SCORES
            AnimatedPrimaryButton(
                text = "View High Scores",
                onClick = onViewHighScores,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // HIGH SCORE DISPLAY
            Text(
                text = "High Score: $highScore",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }
    }
}