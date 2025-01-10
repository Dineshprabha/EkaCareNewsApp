package com.dinesh.ekacarenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.dinesh.ekacarenewsapp.presentation.bottom_navigation.NewsBottomAppBar
import com.dinesh.ekacarenewsapp.presentation.viewmodel.NewsViewModel
import com.dinesh.ekacarenewsapp.ui.theme.EkaCareNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkaCareNewsAppTheme {
                val newsViewModel: NewsViewModel by viewModels()
                NewsBottomAppBar(newsViewModel = newsViewModel)
            }
        }
    }
}

