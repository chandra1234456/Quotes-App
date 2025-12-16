package com.example.quotesapp.presentation.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quotesapp.SoftAmber
import com.example.quotesapp.SoftCoral
import com.example.quotesapp.SoftLeafGreen
import com.example.quotesapp.SoftLilac
import com.example.quotesapp.SoftMintGreen
import com.example.quotesapp.SoftPurple
import com.example.quotesapp.SoftRose
import com.example.quotesapp.SoftSand
import com.example.quotesapp.SoftSkyBlue
import com.example.quotesapp.SoftTeal
import com.example.quotesapp.presentation.data.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuotesViewModel : ViewModel() {

    private val trendingQuotes = listOf(
        Quote(
            "The future belongs to those who believe in the beauty of their dreams.",
            "Eleanor Roosevelt",
            false,
            SoftAmber,
            "Life"
        ),
        Quote(
            "Do what you can, with what you have, where you are.",
            "Theodore Roosevelt",
            false,
            SoftCoral,
            "Motivation"
        ),
        Quote(
            "Success is not final; failure is not fatal.",
            "Winston Churchill",
            false,
            SoftRose,
            "Success"
        ),
        Quote(
            "Wisdom is not a product of schooling but of the lifelong attempt to acquire it.",
            "Albert Einstein",
            false,
            SoftLilac,
            "Wisdom"
        ),
        Quote(
            "Love all, trust a few, do wrong to none.",
            "William Shakespeare",
            false,
            SoftPurple,
            "Love"
        ),
        Quote(
            "Courage is resistance to fear, mastery of fear—not absence of fear.",
            "Mark Twain",
            false,
            SoftSkyBlue,
            "Courage"
        ),
        Quote(
            "Leadership is the capacity to translate vision into reality.",
            "Warren Bennis",
            false,
            SoftTeal,
            "Leadership"
        )
    )
    private val latestQuotes = listOf(
        Quote(
            "Your life does not get better by chance, it gets better by change.",
            "Jim Rohn",
            false,
            SoftMintGreen,
            "Life"
        ),
        Quote(
            "Your growth begins the moment comfort ends.",
            "Liam Hart",
            false,
            SoftLeafGreen,
            "Motivation"
        ),
        Quote(
            "Consistency is the quiet bridge between dreams and results.",
            "Ava Collins",
            false,
            SoftSand,
            "Success"
        ),
        Quote(
            "The best version of you is built, not found.",
            "Evan Blake",
            false,
            SoftAmber,
            "Wisdom"
        ),
        Quote(
            "Love yourself first and everything else falls into line.",
            "Lucille Ball",
            false,
            SoftCoral,
            "Love"
        ),
        Quote(
            "It takes courage to grow up and become who you really are.",
            "E.E. Cummings",
            false,
            SoftRose,
            "Courage"
        ),
        Quote(
            "A leader is one who knows the way, goes the way, and shows the way.",
            "John C. Maxwell",
            false,
            SoftLilac,
            "Leadership"
        )
    )


    private val _uiTrendingState = MutableStateFlow(
        QuotesUiState(quotes = trendingQuotes)
    )
    val uiTrendingState: StateFlow<QuotesUiState> = _uiTrendingState.asStateFlow()

    private val _uiLatestState = MutableStateFlow(
        QuotesUiState(quotes = latestQuotes)
    )
    val uiLatestState: StateFlow<QuotesUiState> = _uiLatestState.asStateFlow()

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