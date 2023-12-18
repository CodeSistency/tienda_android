package com.plcoding.graphqlcountriesapp.presentation.screens.confirmation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.R
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.presentation.common.GoBack

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Confirmation(navController: NavHostController, viewModel: ProductsViewModel) {
    Scaffold(
        topBar = {
            GoBack(navController = navController) {
                
            }
        },
        bottomBar = {
            BottomBarConfirmation(navController = navController)
        }
    ) {
        Column {
            
        }
    }
}

@Composable
fun BottomBarConfirmation(navController: NavHostController) {
    Button(
        onClick = {
            
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Comprar", color = Color.White)
            Spacer(modifier = Modifier.width(3.dp))
            Icon(painterResource(id = R.drawable.ic_bag), contentDescription = null, tint = Color.White)
        }
    }
}