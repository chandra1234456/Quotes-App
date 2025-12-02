package com.example.quotesapp.presentation.viemodel

import androidx.lifecycle.ViewModel
import com.example.quotesapp.latestQuotes
import com.example.quotesapp.presentation.data.Quote
import com.example.quotesapp.trendingQuotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuotesViewModel : ViewModel() {
    private val _uiTrendingState = MutableStateFlow(QuotesUiState(quotes = trendingQuotes))
    val uiTrendingState: StateFlow<QuotesUiState> = _uiTrendingState.asStateFlow()


    fun toggleTrendingTheFavorite(index: Int) {
        val currentList = _uiTrendingState.value.quotes.toMutableList()
        val item = currentList[index]
        currentList[index] = item.copy(isQuoteSelected = !item.isQuoteSelected)
        _uiTrendingState.value = _uiTrendingState.value.copy(quotes = currentList)

    }

    private val _uiLatestState = MutableStateFlow(QuotesUiState(quotes = latestQuotes))
    val uiLatestState: StateFlow<QuotesUiState> = _uiLatestState.asStateFlow()


    fun toggleLatestTheFavorite(index: Int) {
        val currentLatestList = _uiLatestState.value.quotes.toMutableList()
        val item = currentLatestList[index]
        currentLatestList[index] = item.copy(isQuoteSelected = !item.isQuoteSelected)
        _uiLatestState.value = _uiLatestState.value.copy(quotes = currentLatestList)

    }
}

data class QuotesUiState(
    val quotes: List<Quote> = emptyList()
)