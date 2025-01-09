package com.dinesh.ekacarenewsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dinesh.ekacarenewsapp.data.local.NewsDao
import com.dinesh.ekacarenewsapp.data.local.NewsDatabase
import com.dinesh.ekacarenewsapp.data.local.NewsTypeConverter
import com.dinesh.ekacarenewsapp.data.remote.NewsApi
import com.dinesh.ekacarenewsapp.utils.Constants.BASE_URL
import com.dinesh.ekacarenewsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext
        context: Context
    ): NewsDatabase {
        return NewsDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(newsDatabase: NewsDatabase): NewsDao{
        return newsDatabase.newsDao
    }
}