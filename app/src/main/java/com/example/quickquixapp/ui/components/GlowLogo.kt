package com.example.quickquixapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickquixapp.R

@Composable
fun GlowLogo() {

    val glowColor =
        if (isSystemInDarkTheme())
            Color(0xFF8F7CFF)
        else
            Color(0xFFBFAEFF)

    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .size(150.dp)
            .shadow(
                elevation = 30.dp,
                shape = CircleShape,
                ambientColor = glowColor,
                spotColor = glowColor
            )
    ) {
        Image(
            painter = painterResource(R.drawable.quickquix_logo),
            contentDescription = "QuickQuix Logo",
            modifier = Modifier.padding(24.dp)
        )
    }
}