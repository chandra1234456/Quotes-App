package com.example.quotesapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diversity1
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import com.example.quotesapp.presentation.data.Quote


object Constants {
    const val ASYNC_IMAGE =
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fx.com%2Fhappy24_7beauty&psig=AOvVaw2wADdNf3w2doQZzppHioCu&ust=1762962235987000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCOjR_oC46pADFQAAAAAdAAAAABAE"

    const val HOME_SCREEN = "home"
    const val EXPLORE_SCREEN = "explore"
    const val SAVED_SCREEN = "saved"
}

val photoUrls = listOf(
    "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d",
    "https://images.unsplash.com/photo-1499084732479-de2c02d45fcc",
    "https://images.unsplash.com/photo-1519681393784-d120267933ba",
    "https://images.unsplash.com/photo-1483721310020-03333e577078",
    "https://images.unsplash.com/photo-1470770841072-f978cf4d019e"
)

val labels = listOf("Life", "Motivation", "Success", "Wisdom", "Love","Courage","Leadership")

val icons = listOf(
    Icons.Default.Favorite,
    Icons.Default.Star,
    Icons.Default.ShowChart,
    Icons.Default.Info,
    Icons.Default.FavoriteBorder,
    Icons.Default.Security,
    Icons.Default.Diversity1
)

val iconColors = listOf(
    Color(0xFF2B6BF3), // blue
    Color(0xFFFFA43A), // orange
    Color(0xFF2DC28A), // green
    Color(0xFF9B51E0), // purple
    // ★ improved bottom 3 (harmonized)
    Color(0xFF1976D2), // deep blue (better contrast vs first blue)
    Color(0xFFF06292), // vibrant pink, matches saturation of others
    Color(0xFFEF5350), // stronger soft red, consistent with palette
)

val categoryColors = listOf(
    Color(0xFFDDE9FF), // light blue
    Color(0xFFFFEBD9), // light orange
    Color(0xFFDFF6EE), // mint green
    Color(0xFFF2E5FF), // light purple
    // ★ improved bottom 3
    Color(0xFFDCF3FF), // soft sky cyan (pairs with deep blue)
    Color(0xFFF7E6FF), // softer pastel lavender (pairs with pink/purple)
    Color(0xFFEDECEC), // neutral grey (slightly cleaner)
)
val LightYellow      = Color(0xFFFFF3B0)
val LightOrange      = Color(0xFFFFD8A8)
val LightPeach       = Color(0xFFFFC4B2)
val LightPink        = Color(0xFFFFD6E0)
val LightLavender    = Color(0xFFE6D9FF)
val LightPurple      = Color(0xFFD8C7FF)
val LightSkyBlue     = Color(0xFFBEE3FF)
val LightMint        = Color(0xFFC8F7DC)
val LightGreen       = Color(0xFFD9F7BE)
val LightBeige       = Color(0xFFF7EEDB)

val SoftAmber       = Color(0xFFF3B562)
val SoftCoral       = Color(0xFFF28A7F)
val SoftRose        = Color(0xFFE87CA2)
val SoftLilac       = Color(0xFFCE9DF5)
val SoftPurple      = Color(0xFFB89DF5)
val SoftSkyBlue     = Color(0xFF7DC3FF)
val SoftTeal        = Color(0xFF73D0C6)
val SoftMintGreen   = Color(0xFF8CE3A8)
val SoftLeafGreen   = Color(0xFFA5D767)
val SoftSand        = Color(0xFFDDBF8C)

val trendingQuotes = listOf(
    Quote("Success is not final; failure is not fatal.", "Winston Churchill",false,SoftAmber),
    Quote("Do what you can, with what you have, where you are.", "Theodore Roosevelt",false,SoftCoral),
    Quote("The future depends on what you do today.", "Mahatma Gandhi",false,SoftRose),
    Quote("Believe you can and you're halfway there.", "Theodore Roosevelt",false,SoftLilac),
    Quote("Your life does not get better by chance, it gets better by change.", "Jim Rohn",false,SoftPurple)
)


val latestQuotes = listOf(
    Quote("Consistency is the quiet bridge between dreams and results.", "Ava Collins",false,SoftSkyBlue),
    Quote("Your growth begins the moment comfort ends.", "Liam Hart",false,SoftTeal),
    Quote("Small progress is still progress—celebrate it.", "Nova Reed",false,SoftMintGreen),
    Quote("The best version of you is built, not found.", "Evan Blake",false,SoftLeafGreen),
    Quote("Your mindset writes the story long before your actions do.", "Mira Dalton",false,SoftSand)
)






