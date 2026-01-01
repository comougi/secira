package com.ougi.secira.design.theming

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun isDynamicColorAllowed(dynamicColors: Boolean): Boolean = false

@Composable
internal actual fun configureDynamicColors(darkTheme: Boolean): ColorScheme {
    throw Exception("Dynamic color not allowed for ios")
}