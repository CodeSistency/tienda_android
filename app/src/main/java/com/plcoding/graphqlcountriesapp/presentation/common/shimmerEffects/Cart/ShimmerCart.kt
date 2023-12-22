package com.plcoding.graphqlcountriesapp.presentation.common.shimmerEffects.Cart

import alcaravan.fragata.app.common.composables.shimmerEffects.BoxShimmer
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShimmerCart(){
   Scaffold(
       topBar = {
           BoxShimmer(height = 18.dp)
       },
       bottomBar = {
           BoxShimmer(height = 18.dp)
       }
   ) {
       BoxShimmer(height = 40.dp)
       BoxShimmer(height = 40.dp)
       BoxShimmer(height = 40.dp)
   }
}