package com.dinesh.ekacarenewsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val author: String? = null,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
) : Parcelable


fun filterValidArticles(articles: List<Article>): List<Article> {
    return articles.filter { article ->
        article.content != "[Removed]" &&
                article.description != "[Removed]" &&
                article.title != "[Removed]" &&
                !article.content.isNullOrBlank() &&
                !article.description.isNullOrBlank() &&
                !article.title.isNullOrBlank()
    }
}