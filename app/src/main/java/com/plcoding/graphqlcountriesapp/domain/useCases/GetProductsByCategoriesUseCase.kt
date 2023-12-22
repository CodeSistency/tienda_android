package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.model.products.Product

class GetProductsByCategoriesUseCase(
    private val productClient: ProductClient
) {
    suspend fun execute(category: String): List<Product>? {
        Log.e("productos", productClient.getProductsByCategories(category).toString())
        return productClient.getProductsByCategories(category)
    }
}