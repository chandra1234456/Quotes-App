package com.example.quotesapp

import androidx.navigation.NavController
import com.example.quotesapp.navigation.Screen


val tabs = listOf(
    Screen.Home,
    Screen.Explore,
    Screen.Saved
)
fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
