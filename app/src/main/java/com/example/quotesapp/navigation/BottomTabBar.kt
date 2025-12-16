package com.example.quotesapp.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quotesapp.navigateToTab

@Composable
fun BottomTabBar(
    tabs: List<Screen>,
    navController: NavController
) {
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabs.forEach { screen ->
            val selected = currentDestination
                ?.destination
                ?.hierarchy
                ?.any { it.route == screen.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navigateToTab(navController, screen.route)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selected,
                        selectedIcon = screen.selectedIcon,
                        unselectedIcon = screen.unselectedIcon,
                        title = screen.title
                    )
                },
                label = { Text(screen.title) }
            )
        }
    }
}
