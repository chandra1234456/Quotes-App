package com.example.quotesapp.navigation

import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quotesapp.navigateToTab

@Composable
fun SideNavigationRail(
    tabs: List<Screen>,
    navController: NavController
) {
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationRail {
        tabs.forEach { screen ->
            val selected = currentDestination
                ?.destination
                ?.hierarchy
                ?.any { it.route == screen.route } == true

            NavigationRailItem(
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
