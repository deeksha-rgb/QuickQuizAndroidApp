package com.example.quickquixapp.ui.name

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quickquixapp.ui.components.GradientBackground
import com.example.quickquixapp.ui.components.GradientButton

@Composable
fun EnterNameScreen(
    viewModel: EnterNameViewModel,
    onContinue: (String) -> Unit
) {
    //  SAME BACKGROUND AS HOME
    GradientBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  TITLE
            Text(
                text = "Enter your name",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            //  SUBTITLE
            Text(
                text = "This will be used for your score",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(28.dp))

            //  NAME INPUT
            OutlinedTextField(
                value = viewModel.userName,
                onValueChange = viewModel::updateName,
                label = { Text("Your name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor =
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor =
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            // CONTINUE BUTTON (GRADIENT)
            GradientButton(
                text = "Continue",
                onClick = { onContinue(viewModel.userName) }
            )
        }
    }
}