package com.plcoding.graphqlcountriesapp.domain.model.products

data class Product(
    val id: String,
    val titulo: String,
    val precio: Double,
    val descuento: Int?,
    val destacado: Boolean,
    val imgPincipal: String

)
