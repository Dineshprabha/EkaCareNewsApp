package com.dinesh.ekacarenewsapp.presentation.bottom_navigation

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dinesh.ekacarenewsapp.presentation.home.FavoriteScreen
import com.dinesh.ekacarenewsapp.presentation.home.HomeScreen
import com.dinesh.ekacarenewsapp.presentation.home.WebViewScreen
import com.dinesh.ekacarenewsapp.presentation.viewmodel.NewsViewModel
import com.dinesh.ekacarenewsapp.ui.theme.Purple80
import com.google.gson.Gson

@Composable
fun NewsBottomAppBar(modifier: Modifier = Modifier, newsViewModel: NewsViewModel) {

    val navigationController = rememberNavController()
    val selectedItem = remember { mutableStateOf(Screens.Home.screens) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Purple80) {
                NavigationBarItem(
                    selected = selectedItem.value == Screens.Home.screens,
                    onClick = {
                        selectedItem.value = Screens.Home.screens
                        navigationController.navigate(Screens.Home.screens) {
                            popUpTo(Screens.Home.screens) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    label = { Text(text = "Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray
                    )
                )

                NavigationBarItem(
                    selected = selectedItem.value == Screens.Favorite.screens,
                    onClick = {
                        selectedItem.value = Screens.Favorite.screens
                        navigationController.navigate(Screens.Favorite.screens) {
                            popUpTo(Screens.Home.screens) { inclusive = false }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    label = { Text(text = "Favorite") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController, startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screens) {
                val newsList by newsViewModel.newsState.collectAsState()
                val isLoading by newsViewModel.isLoading.collectAsState()
                HomeScreen(articles = newsList, isLoading) { url ->
                    navigationController.navigate("webview?url=${Uri.encode(url)}")
                }
            }
            composable(Screens.Favorite.screens) { FavoriteScreen() }

            // WebView Screen
            composable(
                route = "webview?url={url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                WebViewScreen(url)
            }
        }
    }
}