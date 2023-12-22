package com.plcoding.graphqlcountriesapp.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Categories(viewModel: ProductsViewModel, navController: NavHostController) {
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
                    modifier = Modifier.padding(horizontal = 5.dp).clickable {
                        navController.navigate("categoria/${item.titulo}")
                    }
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