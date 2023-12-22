package com.plcoding.graphqlcountriesapp.presentation.screens.confirmation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plcoding.graphqlcountriesapp.R
import com.plcoding.graphqlcountriesapp.presentation.ProductsViewModel
import com.plcoding.graphqlcountriesapp.presentation.common.GoBack

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Confirmation(navController: NavHostController, viewModel: ProductsViewModel) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var paymentMethod by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var cedula by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            GoBack(navController = navController, title = "Confirmación", content = {})
        },
        bottomBar = {
            BottomBarConfirmation(navController = navController, viewModel, nombre, apellido, cedula, paymentMethod, context)
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(painterResource(id = R.drawable.credit_card_payment_svgrepo_com), contentDescription = null,
                    modifier = Modifier.size(200.dp))
            }
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)){
//                Text(text = "Foto", modifier = Modifier.align(Alignment.Center))
//            }

            Spacer(modifier = Modifier.height(5.dp))
            Column {
                TextField(value = nombre, onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(7.dp))
                TextField(value = apellido, onValueChange = { apellido = it})
                Spacer(modifier = Modifier.height(7.dp))
                TextField(value = cedula, onValueChange = { cedula = it })
                Spacer(modifier = Modifier.height(7.dp))

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
//                IconButton(onClick = { expanded = !expanded }) {
//                    Icon(
//                        imageVector = Icons.Default.MoreVert,
//                        contentDescription = "More"
//                    )
//                }
                Row(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .widthIn(min = 40.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Selecciona un metodo de pago")
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        content = { Text("Entrega") },
                        onClick = { paymentMethod = "Entrega" }
                    )
                    DropdownMenuItem(
                        content = { Text("Delivery") },
                        onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
                    )
                    DropdownMenuItem(
                        content = { Text("Envío Nacional") },
                        onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Nombre:")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = nombre ?: "...")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Cedula")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = cedula ?: "...")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Metodo de pago:")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = paymentMethod ?: "...")
            }
            Divider()
            Text(text = "Pago")


        }
    }
}

@Composable
fun BottomBarConfirmation(
    navController: NavHostController,
    viewModel: ProductsViewModel,
    nombre: String,
    apellido: String,
    cedula: String,
    paymentMethod: String,
    context: Context,
) {
    Button(
        onClick = {
            viewModel.sendMessageToWhatsApp(context,"04121940547",
                "Hola, ${nombre} ${apellido} ${cedula} ${paymentMethod}"
            )
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(text = "Comprar", color = Color.White)
            Spacer(modifier = Modifier.width(3.dp))
            Icon(painterResource(id = R.drawable.ic_bag), contentDescription = null, tint = Color.White)
        }
    }
}