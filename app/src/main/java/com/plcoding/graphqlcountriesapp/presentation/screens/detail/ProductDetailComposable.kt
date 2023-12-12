package com.plcoding.graphqlcountriesapp.presentation.screens.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel

@Composable
fun ProductDetailComposable(
    navController: NavHostController,
    string: String?,
    viewModel: ProductsViewModel
) {
    LaunchedEffect(key1 = true, block = {
        if (string != null) {
            viewModel.getProductDetail(string)
            Log.e("id Composable", string)
        }
    })

    val product = viewModel.stateProduct.collectAsState()

    LaunchedEffect(key1 = product, block = {
        Log.e("productDetail", product.value.producto.toString())
    })
    Box(){
        Text(text = "Detain")
    }
}