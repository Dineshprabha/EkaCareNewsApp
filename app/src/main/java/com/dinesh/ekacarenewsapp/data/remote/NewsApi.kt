package com.dinesh.ekacarenewsapp.data.remote

import com.dinesh.ekacarenewsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("apikey") apikey : String = "BuildConfig.API_KEY"
    ):NewsResponse
}