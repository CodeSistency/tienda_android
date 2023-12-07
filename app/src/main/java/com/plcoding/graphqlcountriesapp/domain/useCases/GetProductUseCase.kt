package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.ProductDetail

class GetProductUseCase(
   private val productClient: ProductClient
) {
    suspend fun execute(code: String): ProductDetail? {
        Log.e("producto", productClient.getProduct(code).toString())
        return productClient.getProduct(code)
    }
}