package com.plcoding.graphqlcountriesapp.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Categories() {
    val pagerState = rememberPagerState { 10 }

    // Store the current page in a state variable
    val currentPage = remember { mutableStateOf(pagerState.currentPage) }

    Box(

    ) {
        HorizontalPager(
            beyondBoundsPageCount = 10,
            state = pagerState,

        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    // Load your image here
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .zIndex(1f)// Add padding for bottom
        ) {
            WormPageIndicator(
                totalPages = pagerState.pageCount,
                currentPage = pagerState.currentPage, // Pass the current page from the state variable
            )
        }
    }
}