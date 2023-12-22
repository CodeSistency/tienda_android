package com.plcoding.graphqlcountriesapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.plcoding.CategoryListQuery
import com.plcoding.CategoryProductsQuery
import com.plcoding.ProductListQuery
import com.plcoding.ProductDetailGlQuery
import com.plcoding.SectionListQuery
import com.plcoding.SliderListQuery

import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductDetail
import com.plcoding.graphqlcountriesapp.domain.model.sections.Sections
import com.plcoding.graphqlcountriesapp.domain.model.slider.Slider

class ApolloProductsClient(
    private val apolloClient: ApolloClient
): ProductClient {

    override suspend fun getProducts(): List<Product> {
        try {
            return apolloClient
                .query(ProductListQuery())
                .execute()
                .data
                ?.productos
                ?.data?.flatMap { product ->
                    val id = product.id ?: ""
                    product.attributes?.toProduct(id)?.let { listOf(it) } ?: emptyList()
                } ?: emptyList()
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch products", e)
            return emptyList()
        }
    }

    override suspend fun getProduct(code: String): ProductDetail? {
        try {
            return apolloClient
                .query(ProductDetailGlQuery(code))
                .execute()
                .data
                ?.producto
                ?.data
                ?.attributes?.toDetailProduct(code)
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch product", e)
            return null
        }
    }

    override suspend fun getCategories(): List<Categories> {
        try {
            return apolloClient
                .query(CategoryListQuery())
                .execute()
                .data
                ?.categorias
                ?.data?.map {
                    it.attributes!!.toCategory()
                } ?: emptyList()
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch categories", e)
            return emptyList()
        }
    }

    override suspend fun getSections(): List<Sections> {
        try {
            return apolloClient
                .query(SectionListQuery())
                .execute()
                .data
                ?.seccions
                ?.data?.map  {
                    it.attributes!!.toSection()
                } ?: emptyList()
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch sections", e)
            return emptyList()
        }
    }

    override suspend fun getSlider(): List<Slider> {
        try {
            return apolloClient
                .query(SliderListQuery())
                .execute()
                .data
                ?.sliderInicios
                ?.data?.map {
                    it.attributes!!.toSlider()
                } ?: emptyList()
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch slider", e)
            return emptyList()
        }
    }

    override suspend fun getProductsByCategories(category: String): List<Product> {
        try {
            return apolloClient
                .query(CategoryProductsQuery(category))
                .execute()
                .data
                ?.productos
                ?.data?.flatMap { product ->
                    val id = product.id ?: ""
                    product.attributes?.toProduct(id)?.let { listOf(it) } ?: emptyList()
                } ?: emptyList()
        } catch (e: ApolloNetworkException) {
            Log.e("Apollo Error", "Failed to fetch products by category", e)
            return emptyList()
        }
    }
}
//class ApolloProductsClient(
//    private val apolloClient: ApolloClient
//): ProductClient {
//
//    override suspend fun getProducts(): List<Product> {
//        val response = apolloClient.query(ProductListQuery()).execute()
//        checkResponseForErrors(response)
//
//        return response.data
//            ?.productos
//            ?.data?.flatMap { product ->
//                val id = product.id ?: ""
//                product.attributes?.toProduct(id)?.let { listOf(it) } ?: emptyList()
//            } ?: emptyList()
//    }
//
//    override suspend fun getProduct(code: String): ProductDetail? {
//        val response = apolloClient.query(ProductDetailGlQuery(code)).execute()
//        checkResponseForErrors(response)
//
//        return response.data
//            ?.producto
//            ?.data
//            ?.attributes?.toDetailProduct(code)
//    }
//
//    override suspend fun getCategories(): List<Categories> {
//        val response = apolloClient.query(CategoryListQuery()).execute()
//        checkResponseForErrors(response)
//
//        return response.data
//            ?.categorias
//            ?.data?.map {
//                it.attributes!!.toCategory()
//            } ?: emptyList()
//    }
//
//    override suspend fun getSections(): List<Sections> {
//        val response = apolloClient.query(SectionListQuery()).execute()
//        checkResponseForErrors(response)
//
//        return response.data
//            ?.seccions
//            ?.data?.map  {
//                it.attributes!!.toSection()
//            } ?: emptyList()
//    }
//
//    override suspend fun getSlider(): List<Slider> {
//        val response = apolloClient.query(SliderListQuery()).execute()
//        checkResponseForErrors(response)
//
//        return response.data
//            ?.sliderInicios
//            ?.data?.map {
//                it.attributes!!.toSlider()
//            } ?: emptyList()
//    }
//
//    private fun checkResponseForErrors(response: ApolloResponse<*>?) {
//        if (response?.hasErrors() == true) {
//            val errors = response.errors?.joinToString(", ") { it.message }
//            throw ApolloException("GraphQL request failed with errors: $errors")
//        }
//    }
//}
