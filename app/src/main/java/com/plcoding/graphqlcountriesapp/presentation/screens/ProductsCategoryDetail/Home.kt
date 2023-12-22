package com.plcoding.graphqlcountriesapp.presentation.screens.ProductsCategoryDetail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Categories
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Item

@Composable
fun ProductsCategoryDetail(
    navController: NavHostController,
    string: String?,
    viewModel: ProductsViewModel
) {
    val listOfProducts = viewModel.stateProductsByCategory.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        if (string != null) {
            viewModel.getProductsByCategory(string)
            Log.e("id category", string)
        }
    })
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 15.dp
    ){
        item(span =  StaggeredGridItemSpan.FullLine){
            Categories(viewModel = viewModel, navController)
        }
        items(listOfProducts.value.productos ?: emptyList()){
            Item(item = it, navController = navController)
        }
    }
}