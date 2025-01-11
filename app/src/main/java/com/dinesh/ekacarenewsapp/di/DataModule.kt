package com.dinesh.ekacarenewsapp.di

import android.app.Application
import androidx.room.Room
import com.dinesh.ekacarenewsapp.data.local.NewsDao
import com.dinesh.ekacarenewsapp.data.local.NewsDatabase
import com.dinesh.ekacarenewsapp.data.local.NewsTypeConverter
import com.dinesh.ekacarenewsapp.data.remote.NewsApi
import com.dinesh.ekacarenewsapp.data.repository.NewsRepositoryImpl
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import com.dinesh.ekacarenewsapp.utils.Constants.BASE_URL
import com.dinesh.ekacarenewsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}