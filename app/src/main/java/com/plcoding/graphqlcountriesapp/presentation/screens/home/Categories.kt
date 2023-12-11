package com.plcoding.graphqlcountriesapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Categories(viewModel: ProductsViewModel) {
    val categories = viewModel.stateCategories.collectAsState()
    Box(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories.value.categorias ?: emptyList()){ item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 5.dp)
                ) {
                    GlideImage(
                        model = item.img,
                        contentDescription = "foto",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = item.titulo,
                        modifier = Modifier.width(60.dp))
                }

            }
        }
    }
}