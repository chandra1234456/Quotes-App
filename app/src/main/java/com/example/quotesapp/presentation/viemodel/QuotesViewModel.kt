package com.example.quotesapp.presentation.viemodel

import android.util.Log
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

    private val _favoriteQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val favoriteQuotes: StateFlow<List<Quote>> = _favoriteQuotes.asStateFlow()

    fun toggleTrendingTheFavorite(index: Int) {
        val currentList = _uiTrendingState.value.quotes.toMutableList()
        val item = currentList[index]
        currentList[index] = item.copy(isQuoteSelected = !item.isQuoteSelected)
        _uiTrendingState.value = _uiTrendingState.value.copy(quotes = currentList)
        // update favorites
        updateFavorites()
    }


    private val _uiLatestState = MutableStateFlow(QuotesUiState(quotes = latestQuotes))
    val uiLatestState: StateFlow<QuotesUiState> = _uiLatestState.asStateFlow()


    fun toggleLatestTheFavorite(index: Int) {
        val currentLatestList = _uiLatestState.value.quotes.toMutableList()
        val item = currentLatestList[index]
        currentLatestList[index] = item.copy(isQuoteSelected = !item.isQuoteSelected)
        _uiLatestState.value = _uiLatestState.value.copy(quotes = currentLatestList)
        updateFavorites()
    }
    fun updateFavorites() {
        // combine all lists you want to check for favorites
        val trendingFavs = _uiTrendingState.value.quotes.filter { it.isQuoteSelected }
        val latestFavs = _uiLatestState.value.quotes.filter { it.isQuoteSelected }
        _favoriteQuotes.value = trendingFavs + latestFavs
        Log.d("TAG", "updateFavorites: ${_favoriteQuotes.value}")
    }

}

data class QuotesUiState(
    val quotes: List<Quote> = emptyList()
)