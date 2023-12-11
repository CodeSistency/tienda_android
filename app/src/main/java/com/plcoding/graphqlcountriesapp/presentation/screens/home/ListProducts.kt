package com.plcoding.graphqlcountriesapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.R
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel

@Composable
fun ListProducts(viewModel: ProductsViewModel, navController: NavHostController) {
    val listOfProducts = viewModel.stateProducts.collectAsState()
    if (listOfProducts.value.isLoading){
        Text(text = "Loading...")
    }else if (!listOfProducts.value.isLoading && listOfProducts.value.productos?.isEmpty() == true){
        Text(text = "No hay productos")
    }else{
        ListOfItems(listOfProducts.value.productos, navController)
    }

}

@Composable
fun ListOfItems(productos: List<Product>?, navController: NavHostController) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 15.dp
        ){
            items(productos ?: emptyList()){ item ->
                Item(item, navController)
            }
        }


}


fun LazyListScope.SubList(productos: List<Product>?, navController: NavHostController){

    items(productos ?: emptyList()){item ->
        Item(item, navController)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Item(
    item: Product,
    navController: NavHostController,
) {
//    Log.e("Products Item", item.toString())

            Card(
                elevation = 4.dp
            ) {
                Column {
                    GlideImage(
                        model = item.imgPincipal,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = item.titulo, style = MaterialTheme.typography.button,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = item.precio.toString(), style = MaterialTheme.typography.caption)
                        Button(
                            onClick = {
                                      navController.navigate("product/${item.id}")
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
                }

            }





        }

