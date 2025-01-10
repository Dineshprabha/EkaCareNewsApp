package com.dinesh.ekacarenewsapp.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinesh.ekacarenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun upsert(article: Article)
//
//    @Delete
//    suspend fun delete(article: Article)
//
//    @Query("SELECT * FROM Article")
//    fun getArticle() : Flow<List<Article>>
//
//    @Query("SELECT * FROM Article WHERE url=:url")
//    suspend fun getArticle(url:String) : Article?
}