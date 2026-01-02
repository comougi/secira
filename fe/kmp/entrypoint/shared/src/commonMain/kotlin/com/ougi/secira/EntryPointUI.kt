package com.ougi.secira

import androidx.compose.runtime.Composable
import com.ougi.secira.design.theming.SeciraTheme
import com.ougi.secira.di.SeciraKoinApplication

@Composable
fun App() {
    SeciraKoinApplication {
        SeciraTheme {
        }
    }
}