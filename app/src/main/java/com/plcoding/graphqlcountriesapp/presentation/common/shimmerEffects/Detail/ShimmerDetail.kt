package com.plcoding.graphqlcountriesapp.presentation.common.shimmerEffects.Detail

import alcaravan.fragata.app.common.composables.shimmerEffects.BoxShimmer
import alcaravan.fragata.app.common.composables.shimmerEffects.CircleShimmer
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShimmerDetail(){
    Scaffold(

    ) {
        BoxShimmer(height = 400.dp)
        BoxShimmer(height = 25.dp)
        Row {
            CircleShimmer()
            Spacer(modifier = Modifier.width(20.dp))
            CircleShimmer()
            Spacer(modifier = Modifier.width(20.dp))
            CircleShimmer()
            Spacer(modifier = Modifier.width(20.dp))
            CircleShimmer()
        }
    }
}