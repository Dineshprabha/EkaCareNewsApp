package com.dinesh.ekacarenewsapp.domain.repository

import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews()

    fun getAllNews() : Flow<Resource<List<Article>>>

}