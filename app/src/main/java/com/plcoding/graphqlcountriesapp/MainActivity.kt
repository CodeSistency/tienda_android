package com.plcoding.graphqlcountriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.presentation.screens.NavigationController
import com.plcoding.graphqlcountriesapp.ui.theme.GraphQlCountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQlCountriesAppTheme {

//                Column {
//                    Text(text = "Algo")
//                }
                preview()

            }
        }
    }

    @Preview
    @Composable
    fun preview(){
        val viewModel = hiltViewModel<ProductsViewModel>()

        NavigationController(viewModel)
    }
}