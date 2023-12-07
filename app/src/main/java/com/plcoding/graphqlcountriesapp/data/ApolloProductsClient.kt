package com.plcoding.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.plcoding.ProductListQuery
import com.plcoding.ProductQuery

import com.plcoding.graphqlcountriesapp.domain.Product
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.ProductDetail

class ApolloProductsClient(
    private val apolloClient: ApolloClient
): ProductClient {
    override suspend fun getProducts(): List<Product> {
        return apolloClient
            .query(ProductListQuery())
            .execute()
            .data
            ?.productos
            ?.data?.map {
                it.attributes!!.toProduct()
            } ?: emptyList()

    }

    override suspend fun getProduct(code: String): ProductDetail? {
        return apolloClient
            .query(ProductQuery(code))
            .execute()
            .data
            ?.producto
            ?.data
            ?.attributes?.toDetailProduct()
    }
}