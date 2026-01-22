package com.example.quickquixapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickquixapp.ui.components.AnimatedPrimaryButton
import com.example.quickquixapp.ui.quiz.QuizViewModel

/**
 * HomeScreen: This is the first screen you see when you open the app.
 * It asks for your name and lets you start the quiz.
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onStartClick: () -> Unit // This function runs when the "Start" button is clicked
) {
    // This variable helps us animate the title when the screen opens
    var showTitle by remember { mutableStateOf(false) }

    // This block runs once as soon as the screen is shown to the user
    LaunchedEffect(Unit) {
        showTitle = true // Start the animation
    }

    // A Column puts items one below the other
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the whole screen
            .background(MaterialTheme.colorScheme.background), // Use the app's background color
        verticalArrangement = Arrangement.Center, // Put items in the middle (top to bottom)
        horizontalAlignment = Alignment.CenterHorizontally // Put items in the middle (left to right)
    ) {

        // The "QuickQuiz" title with a smooth fade-in and scale-up animation
        AnimatedVisibility(
            visible = showTitle,
            enter = fadeIn() + scaleIn(initialScale = 0.4f)
        ) {
            Text(
                text = "QuickQuiz",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(24.dp)) // Adds empty space

        // A text box where you can type your name
        OutlinedTextField(
            value = viewModel.userName,
            onValueChange = { viewModel.updateUserName(it) }, // Update the name in the ViewModel as you type
            label = { Text("Enter your name") },
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        )

        // If the user hasn't typed a name yet, show a red error message
        if (!viewModel.isNameValid) {
            Text(
                text = "Please enter your name to continue",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // The "Start Quiz" button. It only works if you've entered a name.
        AnimatedPrimaryButton(
            text = "Start Quiz",
            onClick = onStartClick,
            enabled = viewModel.isNameValid,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // A button to switch between Light Mode and Dark Mode
        AnimatedPrimaryButton(
            text = if (viewModel.isDarkMode) "Light Mode" else "Dark Mode",
            onClick = { viewModel.toggleDarkMode() }
        )
    }
}
