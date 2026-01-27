package com.example.quickquixapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrandPurple,
    onPrimary = Color.White,

    background = LightBackground,
    onBackground = LightTextPrimary,

    surface = LightSurface,
    onSurface = LightTextPrimary,

    secondary = BrandBlue,
    error = ErrorRed
)

private val DarkColors = darkColorScheme(
    primary = BrandPurple,
    onPrimary = Color.White,

    background = DarkBackground,
    onBackground = DarkTextPrimary,

    surface = DarkSurface,
    onSurface = DarkTextPrimary,

    secondary = BrandBlue,
    error = ErrorRed
)

@Composable
fun QuickQuixAppTheme(
    darkTheme: Boolean ,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}