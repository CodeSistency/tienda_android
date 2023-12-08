package com.plcoding.graphqlcountriesapp.data

import com.plcoding.ProductListQuery
import com.plcoding.ProductQuery
import com.plcoding.graphqlcountriesapp.domain.model.products.Color
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductDetail
import com.plcoding.graphqlcountriesapp.domain.model.products.Talla


fun ProductQuery.Attributes.toDetailProduct(): ProductDetail {
    return ProductDetail(
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

fun ProductListQuery.Attributes.toProduct(): Product {
    return Product(
        titulo = titulo,
        precio = precio,
        descuento = descuento,
        destacado = destacado ?: false,
        imgPincipal = imgPrincipal?.data?.attributes?.url ?: "",
    )
}