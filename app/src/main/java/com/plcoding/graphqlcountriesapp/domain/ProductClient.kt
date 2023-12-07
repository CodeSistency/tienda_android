package com.plcoding.graphqlcountriesapp.domain

import com.plcoding.ProductQuery
import com.plcoding.ProductListQuery

interface ProductClient {
//    suspend fun getProducts(): List<ProductsQuery.Attributes>
//    suspend fun getProduct(): List<ProductQuery.Attributes>

    suspend fun getProducts(): List<Product>
    suspend fun getProduct(code: String): ProductDetail?

}