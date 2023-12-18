package com.plcoding.graphqlcountriesapp.presentation.screens.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductCart
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.utils.formatHtml
import com.plcoding.graphqlcountriesapp.utils.getCartFromSharedPreferences
import com.plcoding.graphqlcountriesapp.utils.saveCartToSharedPreferences
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailComposable(
    navController: NavHostController,
    string: String?,
    viewModel: ProductsViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        if (string != null) {
            viewModel.getProductDetail(string)
//            viewModel.getProductDetail("12")
            Log.e("id Composable", string)
        }
    })

    val product = viewModel.stateProduct.collectAsState()

    // State variables to keep track of selected size and color
    var selectedTalla by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }

    // Function to handle size selection
    fun onTallaSelected(talla: String) {
        selectedTalla = talla
        // Reset selected color when size changes
        selectedColor = ""
    }

    // Function to handle color selection
    fun onColorSelected(color: String) {
        selectedColor = color
    }

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
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = product, block = {
        Log.e("productDetail", product.value.producto.toString())
    })
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Box(modifier = Modifier
                .weight(0.5F)){
                GlideImage(model = product.value.producto?.imgPrincipal,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            LazyColumn(){
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        product.value.producto?.Images?.let{
                            it.forEach {
                                GlideImage(model = it,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(60.dp)
                                                .padding(horizontal = 10.dp))
                            }
                        }

                    }
                    product.value.producto?.titulo?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.button,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    product.value.producto?.descripcion?.let { Text(
                        text = formatHtml(it),
//                        style = MaterialTheme.typography.caption,
                    )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    product.value.producto?.precio?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.button,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp

                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
//                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Sizes (Tallas)
                        Text("Select Size:")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            product.value.producto?.tallas?.map { talla ->
                                TallaRadioButton(
                                    talla = talla.talla,
                                    selectedTalla = selectedTalla,
                                    onTallaSelected = { onTallaSelected(it) }
                                )
                            }
                        }

                        // Colors
                        Text("Select Color:")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            product.value.producto?.tallas?.find { it.talla == selectedTalla }?.color?.map { color ->
                                ColorRadioButton(
                                    color = color.color,
                                    selectedColor = selectedColor,
                                    onColorSelected = { onColorSelected(it) }
                                )
                            }
                        }

                        // Display the selected size and color
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Selected Size: $selectedTalla")
                        Text("Selected Color: $selectedColor")
                        // Button to add product to cart
                        Button(
                            onClick = {

                                if (selectedTalla.isNotEmpty() && selectedColor.isNotEmpty()) {
                                    // Validate that tallas and color are selected

                                    // Check if the product with the same ID, color, and talla is already in the cart
                                    val existingProduct = getCartFromSharedPreferences(context).find {
                                        it.productId == product.value.producto?.id
                                                && it.selectedTalla == selectedTalla
                                                && it.selectedColor == selectedColor
                                    }

                                    if (existingProduct != null) {
                                        // If the product already exists, increment the quantity
                                        val updatedCart = getCartFromSharedPreferences(context).map {
                                            if (it == existingProduct) {
                                                it.copy(quantity = it.quantity + 1)
                                            } else {
                                                it
                                            }
                                        }

                                        // Save the updated cart to SharedPreferences
                                        saveCartToSharedPreferences(updatedCart, context)
                                    } else {
                                        // If the product doesn't exist, add a new entry
                                        val productCart = ProductCart(
                                            productId = product.value.producto?.id.orEmpty(),
                                            title = product.value.producto?.titulo.orEmpty(),
                                            description = product.value.producto?.descripcion.orEmpty(),
                                            category = product.value.producto?.categoria.orEmpty(),
                                            price = product.value.producto?.precio ?: 0.0,
                                            imgPrincipal = product.value.producto?.imgPrincipal.orEmpty(),
                                            selectedTalla = selectedTalla,
                                            selectedColor = selectedColor,
                                            quantity = 1
                                        )

                                        // Retrieve existing cart and add the new product
                                        val updatedCart = getCartFromSharedPreferences(context).toMutableList().apply {
                                            add(productCart)
                                        }

                                        // Save the updated cart to SharedPreferences
                                        saveCartToSharedPreferences(updatedCart, context)
                                    }

                                    // Show a success message
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Product added to cart!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    // Show a toast if tallas or color are not selected
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Please select tallas and color.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Add to Cart")
                        }

//                        ListCart(cartItems = cartItems, onQuantityChanged = {i,p -> })
                    }
                }

            }
        }

    }
}

@Composable
fun TallaRadioButton(
    talla: String,
    selectedTalla: String,
    onTallaSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = if (talla == selectedTalla) Color.Gray else Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onTallaSelected(talla) }
            .padding(16.dp)
    ) {
        Text(
            text = talla,
            color = if (talla == selectedTalla) Color.White else Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ColorRadioButton(
    color: String,
    selectedColor: String,
    onColorSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
                color = if (color == selectedColor) Color.Gray else Color.LightGray,
                shape = CircleShape
            )
            .clickable { onColorSelected(color) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(android.graphics.Color.parseColor(color)),
                    shape = CircleShape
                )
        )
    }
}
//@Composable
//fun ListCart(cartItems: List<ProductCart>, onQuantityChanged: (Int, ProductCart) -> Unit) {
//    LazyColumn {
//        items(cartItems) { cartItem ->
////            CartItem(cartItem = cartItem) {
////                onQuantityChanged(it, cartItem)
////            }
//            CartItem(cartItem = cartItem, onQuantityChanged = {})
//            Divider() // Add a divider between cart items
//        }
//    }
//}
//
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun CartItem(cartItem: ProductCart,  onQuantityChanged: (Int) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        elevation = 8.dp
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            // Image
//            GlideImage(
//                model = cartItem.imgPrincipal,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(MaterialTheme.shapes.medium)
//            )
//
//            // Content
//            Column(
//                modifier = Modifier
//                    .padding(start = 16.dp)
//                    .weight(1f)
//            ) {
//                Text(text = cartItem.title, fontWeight = FontWeight.Bold)
//                Text(text = "${cartItem.price} USD")
//
//                // Quantity Counter
////                QuantityCounter(cartItem, onQuantityChanged = onQuantityChanged)
//            }
//        }
//    }
//}
//@Composable
//fun QuantityCounter(cartItem: ProductCart, onQuantityChanged: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .padding(top = 8.dp)
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        IconButton(
//            onClick = {
//                // Decrement quantity logic
//                if (cartItem.quantity > 1) {
//                    onQuantityChanged(cartItem.quantity - 1)
//                }
//            }
//        ) {
//            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null)
//        }
//
//        Text(
//            text = cartItem.quantity.toString(),
//            style = MaterialTheme.typography.body2,
//            modifier = Modifier.padding(horizontal = 8.dp)
//        )
//
//        IconButton(
//            onClick = {
//                // Increment quantity logic
//                onQuantityChanged(cartItem.quantity + 1)
//            }
//        ) {
//            Icon(imageVector = Icons.Default.Add, contentDescription = null)
//        }
//    }
//}