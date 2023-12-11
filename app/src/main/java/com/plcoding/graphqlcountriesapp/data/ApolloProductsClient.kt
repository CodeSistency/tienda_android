package com.plcoding.graphqlcountriesapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.plcoding.CategoryListQuery
import com.plcoding.ProductListQuery
import com.plcoding.ProductQuery
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
        return apolloClient
            .query(ProductListQuery())
            .execute()
            .data
            ?.productos
            ?.data?.flatMap { product ->
                val id = product.id ?: ""
                product.attributes?.toProduct(id)?.let { listOf(it) } ?: emptyList()
            } ?: emptyList()
    }


//    override suspend fun getProduct(code: String): ProductDetail? {
//        return apolloClient
//            .query(ProductQuery(code))
//            .execute()
//            .data
//            ?.producto
//            ?.data
//            ?.attributes?.toDetailProduct()
//    }
override suspend fun getProduct(code: String): ProductDetail? {
//    Log.e("code id", code)
    return apolloClient
        .query(ProductQuery(code))
        .execute()
        .data
        ?.producto
        ?.data
        ?.let { product ->
            product.id?.let { product.attributes?.toDetailProduct(it) }
        }
}

    override suspend fun getCategories(): List<Categories> {
        return apolloClient
            .query(CategoryListQuery())
            .execute()
            .data
            ?.categorias
            ?.data?.map {
                it.attributes!!.toCategory()
            } ?: emptyList()
    }

    override suspend fun getSections(): List<Sections> {
        return apolloClient
            .query(SectionListQuery())
            .execute()
            .data
            ?.seccions
            ?.data?.map  {
                it.attributes!!.toSection()
            } ?: emptyList()

    }

    override suspend fun getSlider(): List<Slider> {
        return apolloClient
            .query(SliderListQuery())
            .execute()
            .data
            ?.sliderInicios
            ?.data?.map {
                it.attributes!!.toSlider()
            } ?: emptyList()
    }
}