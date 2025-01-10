package com.dinesh.ekacarenewsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.filterValidArticles
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import com.dinesh.ekacarenewsapp.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState = _newsState.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _favoriteArticlesState = MutableStateFlow<List<Article>>(emptyList())
    val favoriteArticlesState = _favoriteArticlesState.asStateFlow()

    private val _bookmarkOperationState = MutableStateFlow<Resource<Unit>?>(null)
    val bookmarkOperationState = _bookmarkOperationState.asStateFlow()


    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        Log.i("TAG", Gson().toJson(result.data))
                        result.data?.let { articles ->
                            _newsState.update { articles }
                        }
                        _isLoading.value = false
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                    }

                    else -> Unit
                }
            }
        }
    }

    fun fetchFavoriteArticles() {
        viewModelScope.launch {
            newsRepository.selectArticles().collectLatest { favorites ->
                _favoriteArticlesState.update { favorites }
            }
        }
    }

    fun favoriteArticle(article: Article) {
        viewModelScope.launch {
            try {
                newsRepository.upsertArticle(article)
                _bookmarkOperationState.update { Resource.Success(Unit) }
                fetchFavoriteArticles()
            } catch (e: Exception) {
                _bookmarkOperationState.update { Resource.Error("Failed to save article") }
            }
        }
    }

    fun removeFavoriteArticle(article: Article) {
        viewModelScope.launch {
            try {
                newsRepository.deleteArticle(article)
                _bookmarkOperationState.update { Resource.Success(Unit) }
                fetchFavoriteArticles()
            } catch (e: Exception) {
                _bookmarkOperationState.update { Resource.Error("Failed to remove article") }
            }
        }
    }
}