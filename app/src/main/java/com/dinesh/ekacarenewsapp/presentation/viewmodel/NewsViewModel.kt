package com.dinesh.ekacarenewsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.ekacarenewsapp.domain.model.Article
import com.dinesh.ekacarenewsapp.domain.model.filterValidArticles
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
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState = _newsState.asStateFlow()

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getAllNews().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                    }

                    is Resource.Success -> {
                        result.data?.let { articles ->
                            val validArticles = filterValidArticles(articles)
                            _newsState.update { validArticles }
                        }
                    }

                    is Resource.Loading -> {

                    }

                    else -> Unit
                }
            }
        }
    }
}