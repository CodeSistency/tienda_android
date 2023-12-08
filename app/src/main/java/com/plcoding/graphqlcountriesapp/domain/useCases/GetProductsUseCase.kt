package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.ProductClient

class GetProductsUseCase(
    private val productClient: ProductClient
) {
    suspend fun execute(): List<Product>? {
        Log.e("productos", productClient.getProducts().toString())
        return productClient.getProducts()
    }
}