package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.slider.Slider

class GetSliderUseCase(
    private val productClient: ProductClient
) {
    suspend fun execute(): List<Slider>? {
        Log.e("productos", productClient.getSlider().toString())
        return productClient.getSlider()
    }
}