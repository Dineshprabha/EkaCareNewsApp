package com.dinesh.ekacarenewsapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.presentation.common.ArticleCard
import com.dinesh.ekacarenewsapp.presentation.common.ArticleShimmerEffect
import com.dinesh.ekacarenewsapp.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(articles: List<Article>, isLoading: Boolean,  onReadMoreClick: (String) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News") },
            )
        }
    ) {
        if (isLoading) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = Dimens.MediumPadding1)
            ) {
                items(5) { // Show 5 shimmer items as a placeholder
                    ArticleShimmerEffect(
                        modifier = Modifier.padding(vertical = Dimens.MediumPadding1)
                    )
                }
            }
        }
        else{
            if (articles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No news available")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(articles) { article ->
                        ArticleCard(
                            article = article,
                            onReadMoreClick = onReadMoreClick
                        )
                    }
                }
            }
        }

    }
}
