package com.dinesh.ekacarenewsapp.presentation.bottom_navigation

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Filled.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Purple80
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screens) {
                            popUpTo(Screens.Home.screens) { inclusive = true }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                    )

                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navigationController.navigate(Screens.Favorite.screens) {
                            popUpTo(Screens.Home.screens)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Search) Color.White else Color.Gray
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController, startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screens) {
                val newsList by newsViewModel.newsState.collectAsState()
                Log.i("TAG", Gson().toJson(newsList))
                HomeScreen(articles = newsList) { url ->
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