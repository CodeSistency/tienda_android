package com.plcoding.graphqlcountriesapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.presentation.screens.ProductsCategoryDetail.ProductsCategoryDetail
import com.plcoding.graphqlcountriesapp.presentation.screens.cart.Cart
import com.plcoding.graphqlcountriesapp.presentation.screens.confirmation.Confirmation
import com.plcoding.graphqlcountriesapp.presentation.screens.detail.ProductDetailComposable
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Home

@Composable
fun NavigationController(viewModel: ProductsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Home(navController, viewModel) }
        composable("product/{productId}") { backStackEntry ->
            ProductDetailComposable(navController, backStackEntry.arguments?.getString("productId"), viewModel)
        }
        composable("cart") { Cart(navController, viewModel) }
        composable("confirmation") { Confirmation(navController, viewModel) }
        composable("categoria/{id}") { backStackEntry ->
            ProductsCategoryDetail(navController, backStackEntry.arguments?.getString("id"), viewModel)
        }


        /*...*/
    }
}