package com.plcoding.graphqlcountriesapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavHostController, viewModel: ProductsViewModel) {
    Scaffold(
        topBar = {
            TopBarContent(navController)
        },
        bottomBar = {
            BottomBarContent(navController)
        }
    ) {
        Box(modifier = Modifier){
            Column {
                Categories()
            }
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