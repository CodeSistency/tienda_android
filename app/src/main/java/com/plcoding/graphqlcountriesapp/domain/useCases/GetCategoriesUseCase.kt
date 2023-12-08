package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories
import com.plcoding.graphqlcountriesapp.domain.model.products.Product

class GetCategoriesUseCase(
    private val productClient: ProductClient
) {
    suspend fun execute(): List<Categories>? {
        Log.e("productos", productClient.getCategories().toString())
        return productClient.getCategories()
    }

}