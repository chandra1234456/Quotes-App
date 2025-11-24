package com.example.quotesapp.presentation.data

import androidx.compose.ui.graphics.Color

data class Quote(
    val text: String,
    val author: String,
    val isQuoteSelected: Boolean = false,
    val quoteBgColor : Color,
)
