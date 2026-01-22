package com.example.quickquixapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * DarkColorScheme: Defines the colors used when the app is in Dark Mode.
 */
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E),    // Slightly lighter dark for cards/boxes
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

/**
 * LightColorScheme: Defines the colors used when the app is in Light Mode.
 */
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    background = Color(0xFFF3EFFF), // Light purple-ish background
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

/**
 * QuickQuixAppTheme: This function wraps the entire app to apply colors and fonts.
 * It chooses between Light and Dark mode based on the user's setting.
 */
@Composable
fun QuickQuixAppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    // Choose which color set to use
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    // Apply the theme to the app's content
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
