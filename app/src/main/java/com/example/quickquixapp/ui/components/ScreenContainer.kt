package com.example.quickquixapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

/**
 * ScreenContainer
 * - Reusable animated wrapper
 * - No color responsibility (theme handled inside screens)
 */
@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it / 6 },
            animationSpec = tween(600)
        ) + fadeIn(animationSpec = tween(600)),
        modifier = modifier
    ) {
        content()
    }
}