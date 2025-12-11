package com.example.quotesapp


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.presentation.ExploreScreen
import com.example.quotesapp.presentation.HomeScreen
import com.example.quotesapp.presentation.SavedScreen
import com.example.quotesapp.ui.theme.QuotesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeTab = TabBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val exploreTab = TabBarItem(
                title = "Explore",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            )
            val savedTab = TabBarItem(
                title = "Saved",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            )

            val tabItems = listOf(homeTab, exploreTab, savedTab)
            val navController = rememberNavController()

            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            QuotesAppTheme {
                Scaffold(
                    bottomBar = {
                        if (!isLandscape) {      // 🔥 Only show in PORTRAIT
                            TabView(tabItems, navController)
                        }
                    }
                ) { padding ->
                    Row(Modifier.fillMaxSize()) {
                        if (isLandscape) {       // 🔥 Show NavigationRail in LANDSCAPE
                            NavigationRailView(tabItems, navController)
                        }
                        NavHost(
                            navController = navController,
                            startDestination = homeTab.title,
                            modifier = Modifier
                                .padding(padding)
                                .weight(1f)
                        ) {
                            composable(homeTab.title) { HomeScreen() }
                            composable(exploreTab.title) { ExploreScreen() }
                            composable(savedTab.title) { SavedScreen() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationRailView(tabBarItems: List<TabBarItem>, navController: NavController) {
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationRail {
        tabBarItems.forEach { item ->

            val selected =
                currentDestination?.destination
                    ?.hierarchy
                    ?.any { it.route == item.title } == true

            NavigationRailItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.title) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    TabBarIconView(
                        isSelected = selected,
                        selectedIcon = item.selectedIcon,
                        unselectedIcon = item.unselectedIcon,
                        title = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}

@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabBarItems.forEach { item ->

            val selected =
                currentDestination?.destination
                    ?.hierarchy
                    ?.any { it.route == item.title } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.title) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    TabBarIconView(
                        isSelected = selected,
                        selectedIcon = item.selectedIcon,
                        unselectedIcon = item.unselectedIcon,
                        title = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(
        badge = {
            if (badgeAmount != null && badgeAmount > 0) {
                Badge {
                    Text(badgeAmount.toString())
                }
            }
        }
    ) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = title,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

