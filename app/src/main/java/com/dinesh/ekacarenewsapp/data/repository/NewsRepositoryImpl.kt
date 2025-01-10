package com.dinesh.ekacarenewsapp.data.repository

import android.util.Log
import com.dinesh.ekacarenewsapp.data.local.NewsDao
import com.dinesh.ekacarenewsapp.data.remote.NewsApi
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.filterValidArticles
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import com.dinesh.ekacarenewsapp.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getAllNews(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())

        try {
            val newsFromApi = newsApi.getNews()
            val articleList = filterValidArticles(newsFromApi.articles)
            newsDao.clearAll()
            newsDao.insertAll(articleList)
        } catch (e: IOException) {
            emit(Resource.Error("Network error. Displaying cached data."))
        } catch (e: HttpException) {
            emit(Resource.Error("Server error. Displaying cached data."))
        }

        val localData = newsDao.getAllArticles().firstOrNull()
        if (!localData.isNullOrEmpty()) {
            emit(Resource.Success(localData))
        } else {
            emit(Resource.Error("No data available."))
        }
    }


    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }

}