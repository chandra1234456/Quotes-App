package com.example.quotesapp


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.navigation.AppNavHost
import com.example.quotesapp.navigation.BottomTabBar
import com.example.quotesapp.navigation.SideNavigationRail
import com.example.quotesapp.ui.theme.QuotesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

            QuotesAppTheme {
                Scaffold(
                    bottomBar = {
                        if (!isLandscape) {
                            BottomTabBar(tabs, navController)
                        }
                    }
                ) { padding ->
                    Row(Modifier.fillMaxSize()) {
                        if (isLandscape) {
                            SideNavigationRail(tabs, navController)
                        }

                        AppNavHost(
                            navController = navController,
                            modifier = Modifier
                                .padding(padding)
                                .weight(1f),
                        )
                    }
                }
            }
        }
    }
}

