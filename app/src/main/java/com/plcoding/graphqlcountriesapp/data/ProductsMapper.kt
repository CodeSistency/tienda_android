package com.plcoding.graphqlcountriesapp.data

import com.plcoding.ProductListQuery
import com.plcoding.ProductQuery
import com.plcoding.graphqlcountriesapp.domain.Product
import com.plcoding.graphqlcountriesapp.domain.ProductDetail

fun ProductQuery.Attributes.toDetailProduct(): ProductDetail {
    return ProductDetail(
        titulo = titulo,
        precio = precio
    )
}

fun ProductListQuery.Attributes.toProduct(): Product {
    return Product(
        titulo = titulo,
        precio = precio,
        descuento = descuento,
        destacado = destacado ?: false,
        imgPincipal = imgPrincipal?.data?.attributes?.url ?: "",
    )
}