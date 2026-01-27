package com.example.quickquixapp.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickquixapp.domain.model.Difficulty
import com.example.quickquixapp.ui.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onStartClick: () -> Unit
) {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { show = true }

    GradientBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GlowLogo()

            Spacer(Modifier.height(24.dp))

            AnimatedVisibility(
                visible = show,
                enter = fadeIn() + scaleIn(initialScale = 0.6f)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "QuickQuix",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Test your knowledge instantly",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(Modifier.height(36.dp))

            Text(
                text = "Choose Difficulty",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Difficulty.values().forEach { level ->
                    FilterChip(
                        selected = viewModel.selectedDifficulty == level,
                        onClick = { viewModel.setDifficulty(level) },
                        label = {
                            Text(
                                level.name.lowercase()
                                    .replaceFirstChar { it.uppercase() }
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(40.dp))

            GradientButton(
                text = "Start Quiz",
                onClick = onStartClick
            )

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = { viewModel.toggleDarkMode() }) {
                Text(
                    if (viewModel.isDarkMode)
                        "Switch to Light Mode"
                    else
                        "Switch to Dark Mode"
                )
            }
        }
    }
}