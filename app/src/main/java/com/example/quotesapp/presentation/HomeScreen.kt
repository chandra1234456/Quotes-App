package com.example.quotesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.quotesapp.categoryColors
import com.example.quotesapp.iconColors
import com.example.quotesapp.icons
import com.example.quotesapp.labels
import com.example.quotesapp.photoUrls
import com.example.quotesapp.presentation.viemodel.QuotesViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier,viewModel: QuotesViewModel = viewModel()) {

    val uiTrendingState by viewModel.uiTrendingState.collectAsState()
    val uiLatestState by viewModel.uiLatestState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Explore",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Awesome quotes from our Community",
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(photoUrls) { url ->
                    FeaturedImageCard(url)
                }
            }
        }

        item { SubHeaders("Latest Quotes", "View All") {} }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(uiTrendingState.quotes) { index, quote ->
                    QuoteCard(
                        quote = quote.text,
                        quotesAuthor = quote.author,
                        bgColor = quote.quoteBgColor,
                        isSelected = quote.isQuoteSelected,
                        onFavoriteClick = { viewModel.toggleTrendingTheFavorite(index) }
                    )
                }
            }
        }

        item { SubHeaders("Categories", "View All") {} }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(7) { index ->
                    CategoryCard(
                        imageVector = icons[index],
                        backgroundColor = categoryColors[index],
                        bottomText = labels[index],
                        color = iconColors[index]
                    )
                }
            }
        }

        item { SubHeaders("Trending Quotes", "View All") {} }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(uiLatestState.quotes) { index, quote ->
                    QuoteCard(
                        quote = quote.text,
                        quotesAuthor = quote.author,
                        bgColor = quote.quoteBgColor,
                        isSelected = quote.isQuoteSelected,
                        onFavoriteClick = { viewModel.toggleLatestTheFavorite(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedImageCard(
    photoUrl: String
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(260.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "Featured Quote Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun QuoteCard(
    quote: String = "Your quote goes here...",
    quotesAuthor: String = "Author Name",
    bgColor: Color,
    isSelected : Boolean,
    onFavoriteClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(16.dp),  // uniform padding
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // ---------- TOP ROW (icons) ----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Profile circle left side
                Box(
                    modifier = Modifier
                        .size(21.dp)
                        .clip(CircleShape)
                    /*.background(Color.LightGray)*/
                )

                // Like + Share on RIGHT
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            onFavoriteClick(isSelected)
                        },
                        imageVector = if (isSelected) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color.White
                    )
                }
            }

            // ---------- QUOTE ----------
            Text(
                text = quote,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 8.dp)
            )

            // ---------- AUTHOR ----------
            Text(
                text = quotesAuthor,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun CategoryCard(
    imageVector: ImageVector,
    backgroundColor: Color,
    bottomText: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Categories Icon",
                    tint = color
                )
            }
            Text(
                text = bottomText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}

@Composable
fun SubHeaders(
    startText: String,
    endText: String,
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = startText,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = endText,
            color = Color(0xFF1976D2),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onNavigate(startText) }
        )
    }
}
