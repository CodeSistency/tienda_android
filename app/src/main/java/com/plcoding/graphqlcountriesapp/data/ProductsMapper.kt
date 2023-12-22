package com.plcoding.graphqlcountriesapp.data

import com.plcoding.CategoryProductsQuery
import com.plcoding.ProductListQuery
import com.plcoding.ProductDetailGlQuery
import com.plcoding.graphqlcountriesapp.domain.model.products.Color
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductDetail
import com.plcoding.graphqlcountriesapp.domain.model.products.Talla


fun ProductDetailGlQuery.Attributes.toDetailProduct(id: String): ProductDetail {
    return ProductDetail(
        id = id,
        titulo = titulo ?: "",
        descripcion = description ?: "",
        destacado = destacado ?: false,
        categoria = categoria.name ?: "",
        precio = precio ?: 0.0,
        imgPrincipal = imgPrincipal?.data?.attributes?.url ?: "",
        Images = imgs?.data?.mapNotNull { it.attributes?.url } ?: emptyList(),
        tallas = tallas?.map { talla ->
            Talla(
                talla = talla?.talla?.name ?: "",
                color = talla?.color?.map { color ->
                    Color(
                        color = color?.color ?: "",
                        stock = color?.stock ?: 0,
                        color_nombre = color?.color_escrito ?: ""
                    )
                } ?: emptyList()
            )
        } ?: emptyList()
    )
}

fun ProductListQuery.Attributes.toProduct(id: String): Product {
    return Product(
        id = id,
        titulo = titulo,
        precio = precio,
        descuento = descuento,
        destacado = destacado ?: false,
        imgPincipal = imgPrincipal?.data?.attributes?.url ?: "",
    )
}

fun CategoryProductsQuery.Attributes.toProduct(id: String): Product {
    return Product(
        id = id,
        titulo = titulo,
        precio = precio,
        descuento = descuento,
        destacado = destacado ?: false,
        imgPincipal = imgPrincipal?.data?.attributes?.url ?: "",
    )
}