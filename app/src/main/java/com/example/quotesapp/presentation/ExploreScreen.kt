package com.example.quotesapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotesapp.R
import com.example.quotesapp.iconColors
import com.example.quotesapp.labels
import com.example.quotesapp.presentation.data.CategoriesItem
import com.example.quotesapp.util.loadQuotesFromRaw
import com.example.quotesapp.util.toastMessage

@Composable
fun ExploreScreen() {
    val context = LocalContext.current
    val quotes = remember { loadQuotesFromRaw(context, R.raw.quotes) } // your JSON file
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val filteredQuotes = remember(selectedCategory, quotes) {
        if (selectedCategory.isNullOrEmpty()) quotes
        else quotes.filter { it.quoteType.contains(selectedCategory!!, ignoreCase = true) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Explore Categories",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(labels.zip(iconColors)) { (label, iconColor) ->
                CategoryCard(
                    label = label,
                    iconColor = iconColor,
                    isSelected = (selectedCategory == label),
                    onClick = {
                        selectedCategory = label
                        context.toastMessage(selectedCategory!!)
                    }
                )
            }
        }
        LazyColumn {
            items(filteredQuotes) { quoteItem ->
                QuotesData(quoteItem)
            }
        }
    }
}

@Composable
fun CategoryCard(
    label: String,
    iconColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, iconColor),
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) iconColor else White,
            contentColor = if (isSelected) White else Black
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QuotesData(quoteItem: CategoriesItem) {
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
                        text = quoteItem.quoteType,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF6200EE) // primary accent color
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Quote Text
                Text(
                    text = quoteItem.quote,
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



