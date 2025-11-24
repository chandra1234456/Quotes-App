package com.example.quotesapp


import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.presentation.ExploreScreen
import com.example.quotesapp.presentation.HomeScreen
import com.example.quotesapp.presentation.SavedScreen
import com.example.quotesapp.ui.WindowSize
import com.example.quotesapp.ui.rememberWindowSize
import com.example.quotesapp.ui.theme.QuotesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // menu tabs
           val homeTab = TabBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val alertsTab = TabBarItem(
                title = "Explore",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
            )
            val settingsTab = TabBarItem(
                title = "Saved",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            )

            val tabBarItems = listOf(homeTab, alertsTab, settingsTab)
            val navController = rememberNavController()

            QuotesAppTheme {
                Scaffold(
                    bottomBar = { TabView(tabBarItems, navController) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    //WindowFunction()  // ✔ now it's inside composition
                    NavHost(
                        navController = navController,
                        startDestination = homeTab.title,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(homeTab.title) { HomeScreen() }
                        composable(alertsTab.title) { ExploreScreen() }
                        composable(settingsTab.title) { SavedScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    val currentDestination by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabBarItems.forEach { tabBarItem ->
            val selected =
                currentDestination?.destination?.hierarchy?.any { it.route == tabBarItem.title } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(tabBarItem.title) {
                        // Prevent multiple copies of the same destination in the back stack
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    TabBarIconView(
                        isSelected = selected,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        title = tabBarItem.title,
                    )
                },
                label = { Text(tabBarItem.title) }
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
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = title
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null && count > 0) {
        Badge {
            Text(count.toString())
        }
    }
}



//@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    QuotesAppTheme {
        HomeScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun HomeNewScreenPreview() {
    QuotesAppTheme {
        HomeNewScreen()
    }
}

@Composable
fun HomeNewScreen(){
    var showWindow by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome")
        Text("Hello Default Device")
        Button(onClick = {
            showWindow = true  // ✔ event updates state
        }) {
            Text("Click me")
        }
    }
    if (showWindow) {
        WindowFunction()  // ✔ now it's inside composition
    }

}
@Composable
fun WindowFunction(){
    val windowSize = rememberWindowSize()
    val context = LocalContext.current

    when (windowSize) {
        WindowSize.Compact -> {
            // Layout optimized for compact screens (e.g., phone portrait)
           HomeScreen()
        }
        WindowSize.Medium -> {
            // Layout optimized for medium screens (e.g., small tablets, phone landscape)
            Row {
                Text("Medium")
                Toast.makeText(context,"Medium", Toast.LENGTH_SHORT).show()
            }
        }
        WindowSize.Expanded -> {
            Toast.makeText(context,"Expanded", Toast.LENGTH_SHORT).show()

            // Layout optimized for expanded screens (e.g., large tablets)
            // TwoPaneLayout { /* ... */ }
        }
    }
}





