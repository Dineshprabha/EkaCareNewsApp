package com.dinesh.ekacarenewsapp.data.remote

import com.dinesh.ekacarenewsapp.data.remote.dto.NewsResponse
import com.dinesh.ekacarenewsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "technology",
        @Query("apikey") apikey : String = Constants.API_KEY
    ):NewsResponse
}