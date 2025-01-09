package com.dinesh.ekacarenewsapp.domain.repository

import com.dinesh.ekacarenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews()

    fun getAllNews() : Flow<List<Article>>

}