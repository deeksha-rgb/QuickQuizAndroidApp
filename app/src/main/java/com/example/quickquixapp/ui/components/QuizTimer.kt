package com.example.quickquixapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizTimer(
    timeLeft: Int,
    totalTime: Int = 10
) {
    val isWarning = timeLeft <= 3

    Text(
        text = "Time left: $timeLeft s",
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        color = if (isWarning)
            MaterialTheme.colorScheme.error        // 🔴 danger
        else
            MaterialTheme.colorScheme.primary      // 🟣 normal
    )

    Spacer(modifier = Modifier.height(6.dp))

    LinearProgressIndicator(
        progress = { timeLeft / totalTime.toFloat() },
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp),
        color = if (isWarning)
            MaterialTheme.colorScheme.error
        else
            MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
    )
}