package com.ougi.secira.design.theming

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
internal actual fun isDynamicColorAllowed(dynamicColors: Boolean): Boolean =
    dynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@Composable
@RequiresApi(Build.VERSION_CODES.S)
internal actual fun configureDynamicColors(darkTheme: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}