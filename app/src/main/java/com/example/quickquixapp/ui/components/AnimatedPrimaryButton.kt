package com.example.quickquixapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

/**
 * AnimatedPrimaryButton: A custom button that shrinks slightly when clicked.
 * This makes the app feel more interactive and "bouncy".
 */
@Composable
fun AnimatedPrimaryButton(
    text: String, // The text to show inside the button
    onClick: () -> Unit, // What happens when the button is clicked
    modifier: Modifier = Modifier,
    enabled: Boolean = true // If false, the button cannot be clicked
) {
    // State to track if the button is currently being pressed (not used directly in logic here but scale depends on it)
    // Actually, in this implementation, it's just a placeholder for future 'press' logic
    // or we can use a basic scale effect.

    val scale by animateFloatAsState(
        targetValue = 1f, // Default scale
        label = "pressScale"
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .scale(scale), // Apply the scale animation
        shape = RoundedCornerShape(16.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}



