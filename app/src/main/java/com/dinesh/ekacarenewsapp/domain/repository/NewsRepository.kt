package com.dinesh.ekacarenewsapp.domain.repository

import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.FavoriteArticle
import com.dinesh.ekacarenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getAllNews() : Flow<Resource<List<Article>>>

    suspend fun upsertFavoriteArticle(favoriteArticle: FavoriteArticle)
    suspend fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle)

    fun getAllFavoriteArticles() : Flow<Resource<List<FavoriteArticle>>>
    fun selectArticles(): Flow<List<Article>>
    suspend fun selectArticle(url : String) : Article?

}