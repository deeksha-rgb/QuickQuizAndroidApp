package com.example.quickquixapp.ui.highscore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickquixapp.QuickQuixApplication
import com.example.quickquixapp.di.ViewModelFactory
import com.example.quickquixapp.ui.highscore.HighScoreViewModel

@Composable
fun HighScoreScreen(
    viewModel: HighScoreViewModel,
    onBack: () -> Unit
) {

    //  Step 1: Get AppContainer from Application
    val appContainer =
        (LocalContext.current.applicationContext as QuickQuixApplication)
            .appContainer

    //  Step 2: Remember Factory (important)
    val factory = remember {
        ViewModelFactory(appContainer)
    }
//
//    //  Step 3: Get ViewModel using Factory
//    val viewModel: HighScoreViewModel = viewModel(factory = factory)

    //  Step 4: Observe state
    val scores = viewModel.userScores
    val highestScore = viewModel.highScore

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ---------- HEADER ----------
        Text(
            text = "Leaderboard",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Highest Score: $highestScore",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- SCORE LIST ----------
        if (scores.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No scores available")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(scores) { userScore ->
                    ScoreItem(
                        name = userScore.userName,
                        score = userScore.highScore,
                        onDelete = {
                            viewModel.deleteScore(userScore.userName)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- CLEAR BUTTON ----------
        Button(
            onClick = { viewModel.clearAllScores() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Clear All Scores")
        }
    }
}


@Composable
fun ScoreItem(
    name: String,
    score: Int,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = "Score: $score")
            }

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

