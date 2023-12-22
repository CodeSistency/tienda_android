package com.plcoding.graphqlcountriesapp.domain

import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductDetail
import com.plcoding.graphqlcountriesapp.domain.model.sections.Sections
import com.plcoding.graphqlcountriesapp.domain.model.slider.Slider

interface ProductClient {
//    suspend fun getProducts(): List<ProductsQuery.Attributes>
//    suspend fun getProduct(): List<ProductQuery.Attributes>

    suspend fun getProducts(): List<Product>
    suspend fun getProduct(code: String): ProductDetail?

    suspend fun getCategories(): List<Categories>
    suspend fun getSections(): List<Sections>

    suspend fun getSlider(): List<Slider>

    suspend fun getProductsByCategories(category: String): List<Product>

}