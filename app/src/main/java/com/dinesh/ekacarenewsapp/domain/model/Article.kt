package com.dinesh.ekacarenewsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    override val id : Int = 0,
    override val author: String? = null,
    override val content: String,
    override val description: String,
    override val publishedAt: String,
    override val source: Source,
    override val title: String,
    override val url: String,
    override val urlToImage: String?,
) : Parcelable, BaseArticle


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