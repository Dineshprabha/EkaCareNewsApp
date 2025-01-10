package com.dinesh.ekacarenewsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.FavoriteArticle
import com.dinesh.ekacarenewsapp.utils.Constants

@Database(entities = [Article::class, FavoriteArticle::class], version = 1, exportSchema = true)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao : NewsDao

}