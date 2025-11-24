package com.example.quotesapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
sealed class WindowSize {
    object Compact : WindowSize()
    object Medium : WindowSize()
    object Expanded : WindowSize()
}


@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    return remember(screenWidthDp) {
        when {
            screenWidthDp < 600.dp -> WindowSize.Compact
            screenWidthDp < 840.dp -> WindowSize.Medium
            else -> WindowSize.Expanded
        }
    }
}

@Composable
fun MyAdaptiveLayout() {
    val windowSize = rememberWindowSize()

    when (windowSize) {
        WindowSize.Compact -> {
            // Layout optimized for compact screens (e.g., phone portrait)
            Column { /* ... */ }
        }
        WindowSize.Medium -> {
            // Layout optimized for medium screens (e.g., small tablets, phone landscape)
            Row { /* ... */ }
        }
        WindowSize.Expanded -> {
            // Layout optimized for expanded screens (e.g., large tablets)
           // TwoPaneLayout { /* ... */ }
        }
    }
}