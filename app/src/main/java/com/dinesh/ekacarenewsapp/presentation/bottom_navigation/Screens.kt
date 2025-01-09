package com.dinesh.ekacarenewsapp.presentation.bottom_navigation

sealed class Screens (val screens: String) {
    data object Home : Screens("Home")
    data object Favorite : Screens("Favorite")
}