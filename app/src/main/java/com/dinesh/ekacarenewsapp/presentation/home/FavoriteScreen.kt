package com.dinesh.ekacarenewsapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinesh.ekacarenewsapp.R
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.BaseArticle
import com.dinesh.ekacarenewsapp.domain.model.FavoriteArticle
import com.dinesh.ekacarenewsapp.presentation.common.ArticleCard
import com.dinesh.ekacarenewsapp.presentation.common.ArticleShimmerEffect
import com.dinesh.ekacarenewsapp.ui.theme.LightBlueWhite
import com.dinesh.ekacarenewsapp.utils.Dimens
import com.dinesh.ekacarenewsapp.utils.Dimens.ExtraSmallPadding2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(articles: List<FavoriteArticle>, isLoading: Boolean, onReadMoreClick: (BaseArticle) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ExtraSmallPadding2)
            .statusBarsPadding()
    ) {
        Text(
            text = "Favorite",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = ExtraSmallPadding2),
            fontSize = 24.sp,
            color = colorResource(id = R.color.text_title)
        )

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
                        .padding(3.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No news available")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp),
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