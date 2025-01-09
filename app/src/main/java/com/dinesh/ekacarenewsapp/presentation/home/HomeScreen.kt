package com.dinesh.ekacarenewsapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinesh.ekacarenewsapp.R
import com.dinesh.ekacarenewsapp.ui.theme.EkaCareNewsAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .statusBarsPadding()
    ) {

        Text(
            text = "News", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_title)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProvideHomeScreen() {
    EkaCareNewsAppTheme {
        HomeScreen()
    }
}