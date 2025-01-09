package com.dinesh.ekacarenewsapp.data.remote.dto

import com.dinesh.ekacarenewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)