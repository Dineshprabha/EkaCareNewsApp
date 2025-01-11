package com.dinesh.ekacarenewsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.BaseArticle
import com.dinesh.ekacarenewsapp.domain.model.FavoriteArticle
import com.dinesh.ekacarenewsapp.domain.repository.NewsRepository
import com.dinesh.ekacarenewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel(){

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _favoriteArticlesState = MutableStateFlow<List<FavoriteArticle>>(emptyList())
    val favoriteArticlesState = _favoriteArticlesState.asStateFlow()

    private val _favoriteOperationState = MutableStateFlow<Resource<Unit>?>(null)
    val favoriteOperationState = _favoriteOperationState.asStateFlow()

    init {
        fetchFavoriteArticles()
    }

    fun fetchFavoriteArticles() {
        viewModelScope.launch {
            newsRepository.getAllFavoriteArticles().collectLatest { favorites ->
                when (favorites) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    is Resource.Success -> {
                        favorites.data?.let { articles ->
                            _favoriteArticlesState.update { articles }
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

    fun favoriteArticle(article: BaseArticle) {
        viewModelScope.launch {
            try {
                val favoriteArticle = FavoriteArticle(
                    author = article.author,
                    content = article.content,
                    description = article.description,
                    publishedAt = article.publishedAt,
                    source = article.source,
                    title = article.title,
                    url = article.url,
                    urlToImage = article.urlToImage
                )
                newsRepository.upsertFavoriteArticle(favoriteArticle)
                _favoriteOperationState.update { Resource.Success(Unit) }
                fetchFavoriteArticles()
            } catch (e: Exception) {
                _favoriteOperationState.update { Resource.Error("Failed to save article") }
            }
        }
    }

    fun removeFavoriteArticle(article: BaseArticle) {
        viewModelScope.launch {
            try {
                val favoriteArticle = FavoriteArticle(
                    author = article.author,
                    content = article.content,
                    description = article.description,
                    publishedAt = article.publishedAt,
                    source = article.source,
                    title = article.title,
                    url = article.url,
                    urlToImage = article.urlToImage
                )
                newsRepository.deleteFavoriteArticle(favoriteArticle)
                _favoriteOperationState.update { Resource.Success(Unit) }
                fetchFavoriteArticles()
            } catch (e: Exception) {
                _favoriteOperationState.update { Resource.Error("Failed to remove article") }
            }
        }
    }
}