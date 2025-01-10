package com.dinesh.ekacarenewsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.utils.Constants

//@Database(entities = [Article::class], version = 1, exportSchema = true)
//abstract class NewsDatabase : RoomDatabase() {
//
//    companion object{
//        fun getInstance(context: Context) = Room.databaseBuilder(context, NewsDatabase::class.java, Constants.NEWS_DATABASE_NAME).build()
//    }
//    abstract val newsDao : NewsDao
//
//}