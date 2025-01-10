package com.dinesh.ekacarenewsapp.data.repository

import com.dinesh.ekacarenewsapp.data.remote.NewsApi
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import com.dinesh.ekacarenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override fun getNews() {
        TODO("Not yet implemented")
    }

    override fun getAllNews(): Flow<Resource<List<Article>>> {
       return flow {
           val newsFromApi = try {
               newsApi.getNews()
           }catch (e:IOException){
               e.printStackTrace()
               emit(Resource.Error("Error loading news"))
               return@flow
           }catch (e:HttpException){
               e.printStackTrace()
               emit(Resource.Error("Error loading news"))
               return@flow
           }
           catch (e:Exception){
               e.printStackTrace()
               emit(Resource.Error("Error loading news"))
               return@flow
           }
           emit(Resource.Success(newsFromApi.articles))
       }

    }

}