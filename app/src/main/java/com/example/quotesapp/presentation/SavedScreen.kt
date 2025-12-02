package com.example.quotesapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.presentation.data.Quote
import com.example.quotesapp.presentation.viemodel.QuotesViewModel


@Composable
fun SavedScreen(viewModel: QuotesViewModel = viewModel()) {
    val uiTrendingState by viewModel.uiTrendingState.collectAsState()
    val favTrendingQuotes = uiTrendingState.quotes.filter { it.isQuoteSelected }
    Log.d("TAG", "SavedScreen: $favTrendingQuotes")

    val uiLatestState by viewModel.uiLatestState.collectAsState()
    val favLatestQuotes = uiLatestState.quotes.filter { it.isQuoteSelected }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        item {
            Text(
                text = "Saved Screen",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // List of saved quotes
        items(favTrendingQuotes) { quote ->
            QuotesData(quote)
        }
    }


}

@Composable
fun QuotesData(quoteItem: Quote) {
    // Use a light background for the screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp), // smooth rounded corners
            colors = CardDefaults.cardColors(containerColor = White), // white card
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // subtle shadow
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Top Row: Icon + Quote Type
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person",
                        tint = Color.Gray
                    )
                    Text(
                        text = "quoteItem.quoteType",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF6200EE) // primary accent color
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Quote Text
                Text(
                    text = "quoteItem.quote",
                    style = MaterialTheme.typography.titleMedium,
                    color = Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Author
                Text(
                    text = "- ${quoteItem.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}