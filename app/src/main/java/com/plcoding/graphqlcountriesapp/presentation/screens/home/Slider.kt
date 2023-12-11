package com.plcoding.graphqlcountriesapp.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun Slider(viewModel: ProductsViewModel) {
    val pagerState = rememberPagerState { viewModel.stateSliders.value.sliders?.size ?: 0 }

    // Store the current page in a state variable
//    val fotos = remember { mutableStateOf(viewModel.stateSliders.value.sliders) }
    val fotos = viewModel.stateSliders.collectAsState()


    LaunchedEffect(key1 =viewModel.stateSliders.value.isLoading , block = {
        Log.e("current pager State", pagerState.currentPage.toString())
        Log.e("pager State size", pagerState.pageCount.toString())
    })

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
//                    .padding(10.dp)
            ) {
                GlideImage(
                    model = fotos.value.sliders?.get(page)?.image,
                    contentDescription = "foto",
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        if (fotos.value.sliders?.isNotEmpty() == true){
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
}

