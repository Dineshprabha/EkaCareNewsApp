package com.dinesh.ekacarenewsapp.domain.model

interface BaseArticle {
    val id: Int
    val author: String?
    val content: String
    val description: String
    val publishedAt: String
    val source: Source
    val title: String
    val url: String
    val urlToImage: String?
}