package com.plcoding.graphqlcountriesapp.presentation.screens.confirmation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
    var paymentMethod by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var cedula by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            GoBack(navController = navController, title = "Confirmación", content = {})
        },
        bottomBar = {
            BottomBarConfirmation(navController = navController, viewModel, nombre, apellido, cedula, paymentMethod)
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)){
                Text(text = "Foto", modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.height(5.dp))

            Column {
                TextField(value = nombre, onValueChange = { nombre = it })
                TextField(value = apellido, onValueChange = { apellido = it})
                TextField(value = cedula, onValueChange = { cedula = it })
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        content = { Text("Entrega") },
                        onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
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
    paymentMethod: Boolean,
) {
    Button(
        onClick = {
            viewModel.sendMessageToWhatsApp("04121940547",
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