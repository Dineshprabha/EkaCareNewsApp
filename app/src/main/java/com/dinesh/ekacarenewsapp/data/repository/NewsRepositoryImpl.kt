package com.dinesh.ekacarenewsapp.data.repository

import com.dinesh.ekacarenewsapp.data.remote.NewsApi
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl: NewsRepository {

    override fun getNews() {
        TODO("Not yet implemented")
    }

    override fun getAllNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

}