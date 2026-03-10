package com.example.audioxel.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Accent,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    error = Red500,
)

@Composable
fun AudioxelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content,
    )
}
