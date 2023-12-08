package com.plcoding.graphqlcountriesapp.domain.model.products

data class ProductDetail(
    val titulo: String,
    val descripcion:  String,
    val destacado: Boolean? = false,
    val categoria: String,
    val precio: Double,
    val imgPrincipal: String,
    val Images: List<String>,
    val tallas: List<Talla>
)

data class Talla(
    val talla: String,
    val color: List<Color>
)

data class Color(
    val color: String,
    val stock: Int,
    val color_nombre: String
)
