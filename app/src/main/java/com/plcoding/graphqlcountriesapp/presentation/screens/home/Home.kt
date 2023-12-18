package com.plcoding.graphqlcountriesapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavHostController, viewModel: ProductsViewModel) {
    val listOfProducts = viewModel.stateProducts.collectAsState()

    Scaffold(
        topBar = {
            TopBarContent(navController)
        },
        bottomBar = {
            BottomBarContent(navController)
        }
    ) {
        Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 30.dp, top = 10.dp)){
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//            ) {
//                item {
//                    Slider(viewModel)
//                }
//                item {
//                    Categories(viewModel)
//                }
////                SubList(listOfProducts.value.productos, navController)
//            }
//            Column {
//                Slider(viewModel = viewModel)
//                Categories(viewModel = viewModel)
//                ListProducts(viewModel = viewModel, navController = navController)
//            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 15.dp
            ){
                item(span =  StaggeredGridItemSpan.FullLine){
                    Slider(viewModel = viewModel)
                }
                item(span =  StaggeredGridItemSpan.FullLine ){
                    Categories(viewModel = viewModel)

                }
                items(listOfProducts.value.productos ?: emptyList()){
                    Item(item = it, navController = navController)
                }
            }

//            LazyVerticalGrid(GridCells.Fixed(2)) {
//                item(span = { GridItemSpan(2) }){
//             Slider(viewModel = viewModel)
//            }
//                item(span = { GridItemSpan(2) }){
//                    Categories(viewModel = viewModel)
//
//            }
//            items(listOfProducts.value.productos ?: emptyList()){
//                   Item(item = it, navController = navController)
//            }
//            }
        }
    }
}

@Composable
fun BottomBarContent(navController: NavHostController) {
    Box(modifier = Modifier){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            //Icon 1
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                Text(text = "Home")
            }
            //Icon 2
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                Text(text = "Home")
            }
            //Icon 3
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                Text(text = "Home")
            }
        }
    }
}

@Composable
fun TopBarContent(navController: NavHostController) {
    Box(modifier = Modifier){
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(id = R.drawable.ic_cart), contentDescription = null)
                }
                Text(text = "Logo")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            }
        }
    }
}