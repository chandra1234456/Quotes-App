package com.example.quotesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quotesapp.presentation.HomeScreen
import com.example.quotesapp.presentation.ExploreScreen
import com.example.quotesapp.presentation.SavedScreen
import com.example.quotesapp.presentation.viemodel.QuotesViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val viewModel: QuotesViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(viewModel)
        }
        composable(Screen.Explore.route) {
            ExploreScreen(viewModel)
        }
        composable(Screen.Saved.route) {
            SavedScreen(viewModel)
        }
    }
}
