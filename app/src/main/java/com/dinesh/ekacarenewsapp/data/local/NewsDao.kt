package com.dinesh.ekacarenewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinesh.ekacarenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Delete
    suspend fun delete(article: Article)

    @Query("DELETE FROM articles")
    suspend fun clearAll()

    @Query("SELECT * FROM articles")
    fun getAllArticles() : Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE url=:url")
    suspend fun getArticle(url:String) : Article?
}