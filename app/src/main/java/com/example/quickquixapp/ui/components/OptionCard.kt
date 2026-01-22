package com.example.quickquixapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionCard(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean?,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isCorrect == true -> Color(0xFF4CAF50)   // Green
            isCorrect == false && isSelected -> Color(0xFFF44336) // Red
            isSelected -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.surfaceVariant
        },
        label = "optionColor"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isCorrect == null) { onClick() },
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
            color = if (isSelected || isCorrect != null)
                Color.White
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
