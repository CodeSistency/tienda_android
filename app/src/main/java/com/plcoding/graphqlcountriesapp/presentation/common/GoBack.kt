package com.plcoding.graphqlcountriesapp.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun GoBack(navController: NavController, content: @Composable (Modifier) -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { navController.popBackStack()}) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        content
    }
}