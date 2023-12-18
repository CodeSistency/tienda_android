package com.plcoding.graphqlcountriesapp.presentation.screens.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.R
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductCart
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.utils.getCartFromSharedPreferences
import com.plcoding.graphqlcountriesapp.utils.saveCartToSharedPreferences

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Cart(navController: NavHostController, viewModel: ProductsViewModel) {
    val context = LocalContext.current
    var cartItems by remember { mutableStateOf(getCartFromSharedPreferences(context)) }
    // Function to handle quantity change
    fun onQuantityChanged(newQuantity: Int, updatedCartItem: ProductCart) {
        // Update quantity in the cart
        val updatedCart = cartItems.map {
            if (it == updatedCartItem) {
                it.copy(quantity = newQuantity)
            } else {
                it
            }
        }
        // Save the updated cart to SharedPreferences
        saveCartToSharedPreferences(updatedCart, context)
        // Update the state variable
        cartItems = updatedCart
    }
    Scaffold(
        topBar = {
            TopBarCart(navController)
        },
        bottomBar = {
            BottomBarCart(navController)
        }
    ) {
        Column {
            Text(text = "Carrito", fontSize = 22.sp)
            Text(text = "items ${cartItems.size}")
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            ListCart(cartItems = cartItems, onQuantityChanged = ::onQuantityChanged)
        }
    }
}

@Composable
fun ListCart(cartItems: List<ProductCart>, onQuantityChanged: (Int, ProductCart) -> Unit) {
    LazyColumn {
        items(cartItems) { cartItem ->
            CartItem(cartItem = cartItem) {
                onQuantityChanged(it, cartItem)
            }
//            CartItem(cartItem = cartItem, onQuantityChanged = {})
//            Divider() // Add a divider between cart items
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItem(cartItem: ProductCart, onQuantityChanged: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Image
            GlideImage(
                model = cartItem.imgPrincipal,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            // Content
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = cartItem.title, fontWeight = FontWeight.Bold)
                Text(text = "${cartItem.price} USD")

                // Quantity Counter
                QuantityCounter(cartItem, onQuantityChanged = onQuantityChanged)
            }
        }
    }
}
@Composable
fun QuantityCounter(cartItem: ProductCart, onQuantityChanged: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                // Decrement quantity logic
                if (cartItem.quantity > 1) {
                    onQuantityChanged(cartItem.quantity - 1)
                }
            }
        ) {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null)
        }

        Text(
            text = cartItem.quantity.toString(),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(
            onClick = {
                // Increment quantity logic
                onQuantityChanged(cartItem.quantity + 1)
            }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun TopBarCart(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
       IconButton(onClick = { }) {
           Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
       }
    }
}

@Composable
fun BottomBarCart(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate("confirmation")
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