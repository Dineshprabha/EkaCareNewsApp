package com.dinesh.ekacarenewsapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dinesh.ekacarenewsapp.domain.model.BaseArticle
import com.dinesh.ekacarenewsapp.presentation.viewmodel.FavoriteViewModel
import com.dinesh.ekacarenewsapp.utils.Resource

@Composable
fun WebViewScreen(
    article: BaseArticle,
    viewModel: FavoriteViewModel,
) {
    val isFavorite = remember { mutableStateOf(false) }
    val bookmarkOperationState by viewModel.favoriteOperationState.collectAsState()

    LaunchedEffect(bookmarkOperationState) {
        bookmarkOperationState?.let { state ->
            when (state) {
                is Resource.Loading -> TODO()
                is Resource.Success -> {
                    viewModel.fetchFavoriteArticles()
                    isFavorite.value = !isFavorite.value
                }
                is Resource.Error -> {
                    TODO()
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(Unit) {
        isFavorite.value = viewModel.favoriteArticlesState.value.any { it.url == article.url }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (isFavorite.value) {
                        viewModel.removeFavoriteArticle(article)
                    } else {
                        viewModel.favoriteArticle(article)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (isFavorite.value) "Unbookmark article" else "Bookmark article"
                )
            }
        }
    ) { paddingValues ->
        // Your WebView content
        AndroidView(
            factory = { context ->
                android.webkit.WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadUrl(article.url)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}