package com.plcoding.graphqlcountriesapp.presentation.common.shimmerEffects.Home

import alcaravan.fragata.app.common.composables.shimmerEffects.BoxShimmer
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Categories
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Item
import com.plcoding.graphqlcountriesapp.presentation.screens.home.Slider

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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalItemSpacing = 15.dp
        ){
            items(6){
                BoxShimmer()
            }
        }

    }
}